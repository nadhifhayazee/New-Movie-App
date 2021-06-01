package com.example.newmovieapp.detail_item.repository

import androidx.lifecycle.LiveData
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State

interface DetailRepository {
    fun getDetailMovie(movieId: String, language: String): LiveData<State<Movie>>
    fun getDetailTv(tvId: String, language: String): LiveData<State<Movie>>
}