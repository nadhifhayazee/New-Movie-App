package com.example.newmovieapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.favorite.helper.InsertResponse
import com.example.newmovieapp.favorite.repository.LocalFavoriteRepository
import com.example.newmovieapp.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val localFavoriteRepository: LocalFavoriteRepository
) : ViewModel() {


    fun getMovies(): LiveData<PagedList<MovieEntity>> {
        return localFavoriteRepository.getAllMovie()
    }

    fun getTvs(): LiveData<PagedList<TvEntity>> {
        return localFavoriteRepository.getAllTv()
    }

    fun insertMovie(movie: MovieEntity): LiveData<State<InsertResponse>> {
        return localFavoriteRepository.insertMovie(movie)
    }

    fun insertTv(tv: TvEntity): LiveData<State<InsertResponse>> {
        return localFavoriteRepository.insertTv(tv)
    }

    fun checkIsFavoriteMovie(movieId: Int): LiveData<State<Boolean>> {
        return localFavoriteRepository.isFavoriteMovie(movieId)
    }

    fun checkIsFavoriteTv(tvId: Int): LiveData<State<Boolean>> {
        return localFavoriteRepository.isFavoriteTv(tvId)
    }



}
