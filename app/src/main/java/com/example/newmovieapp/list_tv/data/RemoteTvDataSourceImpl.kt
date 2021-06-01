package com.example.newmovieapp.list_tv.data

import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.ApiService
import com.example.newmovieapp.network.State
import javax.inject.Inject

class RemoteTvDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteTvDataSource {

    override suspend fun getNowPlayingTv(language: String): State<MovieResponse> {
        apiService.getNowPlayingTv(
            BuildConfig.API_KEY, language
        ).let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }

    override suspend fun getPopularTv(language: String): State<MovieResponse> {
        apiService.getPopularTv(
            BuildConfig.API_KEY, language
        ).let {
            if (it.isSuccessful) return State.success(it.body())
            return State.error(it.errorBody().toString())
        }
    }
}