package com.example.newmovieapp.favorite.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.favorite.helper.InsertResponse
import com.example.newmovieapp.network.State

interface LocalFavoriteRepository  {
    fun getAllMovie(): LiveData<PagedList<MovieEntity>>
    fun insertMovie(movie: MovieEntity): LiveData<State<InsertResponse>>
    fun isFavoriteMovie(movieId: Int): LiveData<State<Boolean>>


    fun getAllTv(): LiveData<PagedList<TvEntity>>
    fun insertTv(tv: TvEntity): LiveData<State<InsertResponse>>
    fun isFavoriteTv(tvId: Int): LiveData<State<Boolean>>


}