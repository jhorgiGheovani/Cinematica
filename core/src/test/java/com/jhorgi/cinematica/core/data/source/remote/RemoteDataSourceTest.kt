package com.jhorgi.cinematica.core.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_DISCOVER_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_NOW_PLAYING_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_POPULAR_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_TOP_RATED_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_UP_COMING_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource.Companion.ARG_DISCOVER_TV_SERIES
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource.Companion.ARG_POPULAR_TV_SERIES
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource.Companion.ARG_TOP_RATED_TV_SERIES
import com.jhorgi.cinematica.core.data.source.remote.data.FakeApiService
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.data.source.remote.utils.DataDummy
import com.jhorgi.cinematica.core.data.source.remote.utils.DataDummy.generateTvDetailsResponse
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.utils.DataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    //Mock the Api Service to simulate the API calls
    @Mock
    private lateinit var apiService: ApiService


    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var fakeRemoteDataSource: RemoteDataSource

    @Before
    fun setUp() {

        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        remoteDataSource = RemoteDataSource(apiService)
        fakeRemoteDataSource = RemoteDataSource(FakeApiService())
    }


    //POPULAR MOVIE LIST
    @Test
    fun `Test Get Popular Movie Success Response`() = runTest {

        //Define behavior of the mock, Simulate the Api Calling
        `when`(apiService.getPopularMovie()).thenReturn(DataDummy.generateListMovieResponse())
        `when`(apiService.movieListGenres()).thenReturn(DataDummy.generateListGenreListResponse())

        val result = remoteDataSource.getPopularMovie().toList()

        assertTrue(result[0] is Resource.Success) // make sure that the result was Resource.Success

        //see the actual value that as expected
        result.map { actualResult ->
            when (actualResult) {
                is Resource.Success -> {
                    assertEquals(
                        DataDummy.dummyListMovieData()[0].title,
                        actualResult.data[0].title
                    )
                }

                is Resource.Error -> {}
            }
        }

    }

    @Test
    fun `Test Get Popular Movie Failed Response`() = runTest {
        `when`(apiService.getPopularMovie()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.movieListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val result = remoteDataSource.getPopularMovie().toList()
        movieHelperFailedResponse(result)
    }


    //Up Coming Movie
    @Test
    fun `Test Up Coming Movie Success Response`() = runTest {
        val remoteDataSource = RemoteDataSource(FakeApiService())

        val actualResult = remoteDataSource.getUpComingMovie().toList()

        val expectedResult = DataDummy.generateUpComingMovieResponse().results.map {
            DataMapper.mapMovieResponseToDomain(it, listOf())
        }

        assertTrue(actualResult[0] is Resource.Success)

        actualResult.map {
            when (it) {
                is Resource.Success -> {
                    assertEquals(expectedResult, it.data)
                }

                is Resource.Error -> {}
            }
        }
    }

    @Test
    fun `Test Up Coming Movie Failed Response`() = runTest {
        `when`(apiService.getUpComingMovie()).then { throw Exception(ERROR_MESSAGE_API) }
        val result = remoteDataSource.getUpComingMovie().toList()
        movieHelperFailedResponse(result)

    }


    //Top Rated Movie
    @Test
    fun `Test Top Rated Movie Success Response`() = runTest {
        val actualResult = fakeRemoteDataSource.getTopRatedMovie().toList()
        movieHelperFunction(actualResult)
    }

    @Test
    fun `Test Top Rated Movie Failed Response`() = runTest {
        `when`(apiService.getTopRatedMovie()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.movieListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val result = remoteDataSource.getTopRatedMovie().toList()
        movieHelperFailedResponse(result)
    }

    //NOW PLAYING MOVIE
    @Test
    fun `Test Now Playing Movie Success Response`() = runTest {
        val actualResult = fakeRemoteDataSource.getNowPlayingMovie().toList()
        movieHelperFunction(actualResult)
    }

    @Test
    fun `Test Now Playing Movie Failed Response`() = runTest {
        `when`(apiService.getNowPlayingMovie()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.movieListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val result = remoteDataSource.getNowPlayingMovie().toList()
        movieHelperFailedResponse(result)
    }

    //DISCOVER MOVIE
    @Test
    fun `Test Discover Movie Success Response`() = runTest {
        val actualResult = fakeRemoteDataSource.discoverMovie().toList()
        movieHelperFunction(actualResult)
    }

    @Test
    fun `Test Discover Movie Failed Response`() = runTest {
        `when`(apiService.discoverMovie()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.movieListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val result = remoteDataSource.discoverMovie().toList()
        movieHelperFailedResponse(result)
    }


    @Test
    fun `Test Discover TV Series Success Response`() = runTest {
        val actualResult = fakeRemoteDataSource.discoverTvShow().toList()
        tvSeriesHelperFunction(actualResult)
    }

    @Test
    fun `Test Discover TV Series Failed Response`() = runTest {
        `when`(apiService.discoverTvShow()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.tvListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val actualResult = remoteDataSource.discoverTvShow().toList()
        tvSeriesHelperFailedResponse(actualResult)
    }

    //Popular TvSeries
    @Test
    fun `Test Popular TV Series Success Response`() = runTest {
        val actualResult = fakeRemoteDataSource.getPopularTvShow().toList()
        tvSeriesHelperFunction(actualResult)
    }

    @Test
    fun `Test Popular TV Series Failed Response`() = runTest {
        `when`(apiService.getPopularTvShow()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.tvListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val actualResult = remoteDataSource.getPopularTvShow().toList()
        tvSeriesHelperFailedResponse(actualResult)
    }

    //Top Rated TvSeries
    @Test
    fun `Test Top Rated TV Series Success Response`() = runTest {
        val actualResult = fakeRemoteDataSource.getTopRatedTvShows().toList()
        tvSeriesHelperFunction(actualResult)
    }

    @Test
    fun `Test Top Rated TV Series Failed Response`() = runTest {
        `when`(apiService.getTopRatedTvShows()).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.tvListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val actualResult = remoteDataSource.getTopRatedTvShows().toList()
        tvSeriesHelperFailedResponse(actualResult)
    }


    @Test
    fun `test popular movie paging source loads data correctly `() = runBlocking {
        val remoteDataSource = RemoteDataSource(FakeApiService())
        val moviePagingSource = remoteDataSource.moviePagingSource(ARG_POPULAR_MOVIE)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("Movie 1", result.data[0].title)
    }

    @Test
    fun `test discover movie paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.moviePagingSource(ARG_DISCOVER_MOVIE)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("Movie 1", result.data[0].title)
    }

    @Test
    fun `test now playing movie paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.moviePagingSource(ARG_NOW_PLAYING_MOVIE)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("Movie 1", result.data[0].title)
    }

    @Test
    fun `test top rated movie paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.moviePagingSource(ARG_TOP_RATED_MOVIE)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("Movie 1", result.data[0].title)

    }

    @Test
    fun `test up coming movie paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.moviePagingSource(ARG_UP_COMING_MOVIE)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("Up coming movie 0", result.data[0].title)
    }

    @Test
    fun `test discover tv series paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.tvSeriesPagingSource(ARG_DISCOVER_TV_SERIES)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("title 0", result.data[0].title)
    }

    @Test
    fun `test popular tv series paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.tvSeriesPagingSource(ARG_POPULAR_TV_SERIES)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("title 0", result.data[0].title)

    }

    @Test
    fun `test top rated tv series paging source loads data correctly `() = runBlocking {
        val moviePagingSource = fakeRemoteDataSource.tvSeriesPagingSource(ARG_TOP_RATED_TV_SERIES)
        val pager = TestPager(PagingConfig(pageSize = NETWORK_PAGE_SIZE), moviePagingSource)
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertEquals("title 0", result.data[0].title)
    }


    @Test
    fun `test movie cast`() = runTest {

        val expectedResult = DataMapper.mapCreditsResponseToCastCreditModel(DataDummy.generateCreditsResponse())
        val actualResult = fakeRemoteDataSource.getMovieCast(movieId = 2).toList()

        actualResult.map {
            assertEquals(expectedResult, it)
        }


    }

    @Test
    fun `test movie crew`() = runTest {
        val expectedResult = DataMapper.mapCreditsResponseToCrewCreditModel(DataDummy.generateCreditsResponse())
        val actualResult = fakeRemoteDataSource.getMovieCrews(movieId = 2).toList()

        actualResult.map {
            assertEquals(expectedResult, it)
        }

    }

    @Test
    fun `test tv series cast`() = runTest {
        val actualResult = fakeRemoteDataSource.getTvCast(2).toList()
        val expectedResult = DataMapper.mapCreditsResponseToCastCreditModel(DataDummy.generateCreditsResponse())

        actualResult.map {
            assertEquals(expectedResult,it)
        }

    }

    @Test
    fun `test tv series crew`() = runTest {
        val actualResult = fakeRemoteDataSource.getTvCrews(2).toList()
        val expectedResult = DataMapper.mapCreditsResponseToCrewCreditModel(DataDummy.generateCreditsResponse())

        actualResult.map {
            assertEquals(expectedResult,it)
        }
    }

    @Test
    fun `test movie details`() = runTest {
        val actualResult = fakeRemoteDataSource.getMovieDetails(2).toList()
        val expectedResult = DataMapper.mapMovieDetailsResponseToMovieDetails(DataDummy.generateMovieDetailsResponse())

        actualResult.map {
            assertEquals(expectedResult, it)
        }


    }

    @Test
    fun `test tv series details`() = runTest {

        val actualResult = fakeRemoteDataSource.getTvSeriesDetails(2).toList()
        val expectedResult = DataMapper.mapTvDetailsResponseToDomain(generateTvDetailsResponse())

        actualResult.map {
            assertEquals(expectedResult, it)
        }

    }

    @Test
    fun `test movie search`() = runTest {
        val actualResult = fakeRemoteDataSource.movieSearch("spider").toList()
        movieHelperFunction(actualResult)
    }

    @Test
    fun `test movie search failed`() = runTest {
        `when`(apiService.moviesSearch("spider")).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.movieListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val result = remoteDataSource.movieSearch("spider").toList()
        movieHelperFailedResponse(result)

    }


    @Test
    fun `test tv series search`() = runTest {
        val actualResult = fakeRemoteDataSource.tvSeriesSearch("spider").toList()
        tvSeriesHelperFunction(actualResult)
    }

    @Test
    fun `test tv series search failed`() = runTest {
        `when`(apiService.tvSearch("spider")).then { throw Exception(ERROR_MESSAGE_API) }
        `when`(apiService.tvListGenres()).thenReturn(DataDummy.generateListGenreListResponse())
        val actualResult = remoteDataSource.tvSeriesSearch("spider").toList()
        tvSeriesHelperFailedResponse(actualResult)
    }
    companion object {
        private const val ERROR_MESSAGE_API = "Sorry failed to called API"
    }

    private fun tvSeriesHelperFunction(input: List<Resource<List<TvSeries>>>) = run {
        val expectedResult = DataDummy.dummyListTvSeriesData()
        assertTrue(input[0] is Resource.Success)
        input.map {
            when (it) {
                is Resource.Success -> {
                    assertEquals(expectedResult[0].title, it.data[0].title)
                }

                is Resource.Error -> {}
            }
        }
    }

    private fun movieHelperFunction(actualResult: List<Resource<List<Movie>>>) = run {
        val expectedResult = DataDummy.dummyListMovieData()

        assertTrue(actualResult[0] is Resource.Success)
        actualResult.map {
            when (it) {
                is Resource.Success -> {
                    assertEquals(expectedResult[0].title, it.data[0].title)
                }

                is Resource.Error -> {}
            }
        }

    }

    private fun tvSeriesHelperFailedResponse(input: List<Resource<List<TvSeries>>>) = run {
        assertTrue(input.isNotEmpty()) //make sure that the result doesn't error
        assertTrue(input[0] is Resource.Error)

        input.map {
            when (it) {
                is Resource.Success -> {}
                is Resource.Error -> {
                    assertEquals(
                        ERROR_MESSAGE_API,
                        it.error
                    )//Make sure that the error message is correct
                }
            }
        }
    }

    private fun movieHelperFailedResponse(input: List<Resource<List<Movie>>>) = run {
        assertTrue(input.isNotEmpty()) //make sure that the result doesn't error
        assertTrue(input[0] is Resource.Error)

        input.map {
            when (it) {
                is Resource.Success -> {}
                is Resource.Error -> {
                    assertEquals(
                        ERROR_MESSAGE_API,
                        it.error
                    )//Make sure that the error message is correct
                }
            }
        }
    }

}