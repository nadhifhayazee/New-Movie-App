package com.example.newmovieapp.detail_item

import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.NetworkProvider
import com.example.newmovieapp.network.State

class RemoteDetailRepository {

    suspend fun getDetailMovie(
        movieId: String?,
        language: String
    ): State<Movie> {
        NetworkProvider.apiService?.getDetailMovie(
            movieId, BuildConfig.API_KEY, language
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }

    suspend fun getDetailTv(
        tvId: String?,
        language: String
    ): State<Movie> {
        NetworkProvider.apiService?.getDetailTv(
            tvId, BuildConfig.API_KEY, language
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }
}