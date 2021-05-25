package com.example.newmovieapp.di

import com.example.newmovieapp.detail_item.RemoteDetailRepository
import com.example.newmovieapp.list_movie.RemoteMovieRepository
import com.example.newmovieapp.list_tv.RemoteTvRepository

object RepositoryProvider {
    var movieRepository: RemoteMovieRepository? = null
    var tvRepository: RemoteTvRepository? = null
    var detailRepository: RemoteDetailRepository? = null

    fun initialize() {
        initRepository()
    }

    private fun initRepository() {
        movieRepository = RemoteMovieRepository()
        tvRepository = RemoteTvRepository()
        detailRepository = RemoteDetailRepository()
    }
}