package com.example.newmovieapp.list_movie.data

import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.State

interface RemoteMovieDataSource {
    suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): State<MovieResponse>

    suspend fun getPopularMovies(language: String): State<MovieResponse>
}