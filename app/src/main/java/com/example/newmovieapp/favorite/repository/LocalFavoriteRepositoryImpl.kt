package com.example.newmovieapp.favorite.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.favorite.data.LocalFavoriteDataSource
import com.example.newmovieapp.favorite.helper.InsertResponse
import com.example.newmovieapp.network.State
import javax.inject.Inject

class LocalFavoriteRepositoryImpl @Inject constructor(
    private val localFavoriteDataSource: LocalFavoriteDataSource
) : LocalFavoriteRepository {
    override fun getAllMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localFavoriteDataSource.getAllMovies(), config).build()
    }


    override fun insertMovie(movie: MovieEntity): LiveData<State<InsertResponse>> = liveData {
        val movieExist = localFavoriteDataSource.getMovie(movie.movie_id ?: 0)
        if (movieExist != null) {
            Log.d("movie", movieExist.movie_id.toString())
            localFavoriteDataSource.deleteMovie(movieExist)
            emit(State.success(InsertResponse.DELETED))
        } else {
            localFavoriteDataSource.insertMovie(movie)
            emit(State.success(InsertResponse.INSERTED))
        }
    }


    override fun isFavoriteMovie(movieId: Int): LiveData<State<Boolean>> = liveData {
        val movie = localFavoriteDataSource.getMovie(movieId)
        emit(State.success(movie != null))
    }

    override fun getAllTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localFavoriteDataSource.getAllTvs(), config).build()
    }

    override fun insertTv(tv: TvEntity): LiveData<State<InsertResponse>> = liveData {
        val tvExist = localFavoriteDataSource.getTv(tv.tv_id ?: 0)
        if (tvExist != null) {
            Log.d("movie", tvExist.tv_id.toString())
            localFavoriteDataSource.deleteTv(tvExist)
            emit(State.success(InsertResponse.DELETED))
        } else {
            localFavoriteDataSource.insertTv(tv)
            emit(State.success(InsertResponse.INSERTED))
        }
    }


    override fun isFavoriteTv(tvId: Int): LiveData<State<Boolean>> = liveData {
        val tv = localFavoriteDataSource.getTv(tvId)
        emit(State.success(tv != null))
    }


}