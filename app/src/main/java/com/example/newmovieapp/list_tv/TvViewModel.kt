package com.example.newmovieapp.list_tv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmovieapp.di.RepositoryProvider
import com.example.newmovieapp.model.MovieResponse
import com.example.newmovieapp.network.State
import kotlinx.coroutines.launch

class TvViewModel : ViewModel() {

    private val _popularTv = MutableLiveData<State<MovieResponse>>()
    private val _nowPlayingTv = MutableLiveData<State<MovieResponse>>()


    val popularTv get() = _popularTv
    val nowPlayingTv get() = _nowPlayingTv

    fun requestPopularTv(language: String = "id-ID") {
        _popularTv.value = State.loading()
        viewModelScope.launch {
            _popularTv.value = RepositoryProvider.tvRepository?.getPopularTv(language)
        }
    }

    fun requestNowPlayingTv(language: String = "id-ID") {
        _nowPlayingTv.value = State.loading()
        viewModelScope.launch {
            _nowPlayingTv.value = RepositoryProvider.tvRepository?.getNowPlayingTv(language)
        }
    }


}