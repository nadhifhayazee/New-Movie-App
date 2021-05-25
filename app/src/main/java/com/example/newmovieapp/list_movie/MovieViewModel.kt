package com.example.newmovieapp.list_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmovieapp.di.RepositoryProvider
import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.State
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<State<MovieResponse>>()
    private val _popularMovies = MutableLiveData<State<MovieResponse>>()


    val nowPlayingMovies get() = _nowPlayingMovies
    val popularMovies get() = _popularMovies

    init {
        requestNowPlayingMovies()
        requestPopularMovies()
    }

    private fun requestNowPlayingMovies(
        language: String = "id-ID",
        page: Int = 1
    ) {
        _nowPlayingMovies.value = State.loading()
        viewModelScope.launch {
            _nowPlayingMovies.value = RepositoryProvider.movieRepository?.getNowPlayingMovies(language, page)
        }
    }


    private fun requestPopularMovies(
        language: String = "id-ID"
    ) {
        _popularMovies.value = State.loading()
        viewModelScope.launch {
            _popularMovies.value = RepositoryProvider.movieRepository?.getPopularMovies(language)
        }
    }


}