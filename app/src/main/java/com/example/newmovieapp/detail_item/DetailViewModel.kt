package com.example.newmovieapp.detail_item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmovieapp.di.RepositoryProvider
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _detailMovie = MutableLiveData<State<Movie>>()
    private val _detailTv = MutableLiveData<State<Movie>>()

    val detailMovie get() = _detailMovie
    val detailTv get() = _detailTv


    fun getDetailMovie(
        movieId: String,
        language: String = "id-ID"
    ) {
        _detailMovie.value = State.loading()
        viewModelScope.launch {
            _detailMovie.value = RepositoryProvider.detailRepository?.getDetailMovie(movieId, language)
        }
    }

    fun getDetailTv(tvId: String, language: String = "id-ID") {
        _detailTv.value = State.loading()
        viewModelScope.launch {
            _detailTv.value = RepositoryProvider.detailRepository?.getDetailTv(tvId, language)
        }
    }
}