package com.example.newmovieapp.list_tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newmovieapp.list_tv.repository.TvRepository
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val remoteTvRepository: TvRepository) : ViewModel() {

    fun getNowPlayingTvs(
        language: String = "id-ID"
    ): LiveData<State<ArrayList<Movie>>> = remoteTvRepository.getNowPlayingTv(language)

    fun getPopularTvs(
        language: String = "id-ID"
    ): LiveData<State<ArrayList<Movie>>> = remoteTvRepository.getPopularTv(language)


}