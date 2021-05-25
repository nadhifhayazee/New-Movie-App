package com.example.newmovieapp.list_movie

import com.example.newmovieapp.BuildConfig.API_KEY
import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.NetworkProvider
import com.example.newmovieapp.network.State

class RemoteMovieRepository {

    suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): State<MovieResponse> {
        NetworkProvider.apiService?.getNowPlayingMovies(
            API_KEY, language, page
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }

    suspend fun getTrendingMovies(
        language: String
    ): State<MovieResponse> {
        NetworkProvider.apiService?.getTrendingMovies(
            API_KEY, language
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }

    suspend fun getPopularMovies(
        language: String
    ): State<MovieResponse> {
        NetworkProvider.apiService?.getPopularMovies(
            API_KEY, language
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }

}