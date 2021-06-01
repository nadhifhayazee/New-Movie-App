package com.example.newmovieapp.list_tv.repository

import androidx.lifecycle.LiveData
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State

interface TvRepository {
    fun getNowPlayingTv(language: String): LiveData<State<ArrayList<Movie>>>
    fun getPopularTv(language: String): LiveData<State<ArrayList<Movie>>>

}