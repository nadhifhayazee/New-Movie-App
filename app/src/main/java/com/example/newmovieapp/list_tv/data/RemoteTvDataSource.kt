package com.example.newmovieapp.list_tv.data

import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.State

interface RemoteTvDataSource {
    suspend fun getNowPlayingTv(language: String): State<MovieResponse>

    suspend fun getPopularTv(language: String): State<MovieResponse>
}