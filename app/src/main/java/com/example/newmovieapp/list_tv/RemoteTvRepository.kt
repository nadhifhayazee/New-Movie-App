package com.example.newmovieapp.list_tv

import com.example.newmovieapp.BuildConfig.API_KEY
import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.NetworkProvider
import com.example.newmovieapp.network.State

class RemoteTvRepository {

    suspend fun getPopularTv(
        language: String
    ): State<MovieResponse> {
        NetworkProvider.apiService?.getPopularTv(
            API_KEY, language
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }

    suspend fun getNowPlayingTv(
        language: String
    ): State<MovieResponse> {
        NetworkProvider.apiService?.getNowPlayingTv(
            API_KEY, language
        ).let {
            if (it?.isSuccessful == true) return State.success(it.body())
            return State.error(it?.errorBody().toString())
        }
    }

}