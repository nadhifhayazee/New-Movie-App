package com.example.newmovieapp.list_movie.data

import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.ApiService
import com.example.newmovieapp.network.State
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteMovieDataSource {
    override suspend fun getNowPlayingMovies(language: String, page: Int): State<MovieResponse> {
        apiService.getNowPlayingMovies(
            BuildConfig.API_KEY, language, page
        ).let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }

    override suspend fun getPopularMovies(language: String): State<MovieResponse> {
        apiService.getPopularMovies(
            BuildConfig.API_KEY, language
        ).let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }
}