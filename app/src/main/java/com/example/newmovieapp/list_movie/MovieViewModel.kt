package com.example.newmovieapp.list_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newmovieapp.list_movie.repository.MovieRepository
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val remoteMovieRepository: MovieRepository) :
    ViewModel() {


    fun getNowPlayingMovies(
        language: String = "id-ID",
        page: Int = 1
    ): LiveData<State<ArrayList<Movie>>> = remoteMovieRepository.getNowPlayingMovies(language, page)

    fun getPopularMovies(
        language: String = "id-ID"
    ): LiveData<State<ArrayList<Movie>>> = remoteMovieRepository.getPopularMovies(language)


}