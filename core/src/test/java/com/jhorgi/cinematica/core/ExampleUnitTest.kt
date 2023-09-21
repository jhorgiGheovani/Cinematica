package com.jhorgi.cinematica.core

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    fun getMovieGenres(ids: List<Int>): List<String> {
        val genres = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western"
        )
        return ids.map { genres[it].toString() }
    }

    val inputIds = listOf(28, 12, 37, 53)
    @Test
    fun main() {
        val inputIds = listOf(28, 12, 37, 53)
        val test = getMovieGenres(inputIds)
        println("Movie Genres: $test")
    }
}