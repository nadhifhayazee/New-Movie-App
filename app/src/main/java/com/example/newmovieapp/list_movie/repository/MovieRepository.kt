package com.example.newmovieapp.list_movie.repository

import androidx.lifecycle.LiveData
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State

interface MovieRepository {
    fun getNowPlayingMovies(language: String, page: Int): LiveData<State<ArrayList<Movie>>>
    fun getPopularMovies(language: String): LiveData<State<ArrayList<Movie>>>

}