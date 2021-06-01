package com.example.newmovieapp.detail_item.data

import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.ApiService
import com.example.newmovieapp.network.State
import javax.inject.Inject

class RemoteDetailDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDetailDataSource {

    override suspend fun getDetailMovie(movieId: String, language: String): State<Movie> {
        apiService.getDetailMovie(
            movieId,
            BuildConfig.API_KEY, language
        ).let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }

    override suspend fun getDetailTv(tvId: String, language: String): State<Movie> {
        apiService.getDetailTv(
            tvId,
            BuildConfig.API_KEY, language
        ).let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }
}