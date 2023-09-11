package com.jhorgi.cinematica.core.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jhorgi.cinematica.core.data.MovieRepository
import com.jhorgi.cinematica.core.data.source.local.LocalDataSource
import com.jhorgi.cinematica.core.data.source.local.room.MovieDatabase
import com.jhorgi.cinematica.core.data.source.remote.RemoteDataSource
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    val passphrase: ByteArray = SQLiteDatabase.getBytes("CinematicaEncrytedPassword".toCharArray())
    val factory = SupportFactory(passphrase)
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "FavoriteMovies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {

    val apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNTllMGFmMThlM2M2NzU4ODY5ZTJlNjU1MmJkYjUwOCIsInN1YiI6IjY0ZDA3YjMzNjdlMGY3MDBlNGQ2MTRlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ALQLviQMqioLar0vnLULAfB3Es2T3cZ4zkctAzeKMiA"

    single {

        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/5VLcahb6x4EvvFrCF2TePZulWqrLHS2jCg9Ywv6JHog=")
            .build()

        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", apiKey)
                .build()
            chain.proceed(requestHeaders)
        }

        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .addInterceptor(ChuckerInterceptor(androidContext()))
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
}
