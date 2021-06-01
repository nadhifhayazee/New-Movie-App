package com.example.newmovieapp.detail_item.data

import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State

interface RemoteDetailDataSource {
    suspend fun getDetailMovie(movieId: String, language: String): State<Movie>
    suspend fun getDetailTv(tvId: String, language: String): State<Movie>

}