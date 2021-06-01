package com.example.newmovieapp.detail_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newmovieapp.detail_item.repository.DetailRepository
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val remoteDetailRepository: DetailRepository) :
    ViewModel() {

    fun getDetailMovie(
        movieId: String,
        language: String = "id-ID"
    ): LiveData<State<Movie>> = remoteDetailRepository.getDetailMovie(movieId, language)

    fun getDetailTv(tvId: String, language: String = "id-ID"): LiveData<State<Movie>> =
        remoteDetailRepository.getDetailTv(tvId, language)
}