package com.example.newmovieapp.favorite.data

import androidx.paging.DataSource
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity

interface LocalFavoriteDataSource {
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>
    suspend fun getMovie(movieId: Int): MovieEntity?
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun deleteMovie(movie: MovieEntity)

    fun getAllTvs(): DataSource.Factory<Int, TvEntity>
    suspend fun getTv(tvId: Int): TvEntity?
    suspend fun insertTv(tv: TvEntity)
    suspend fun deleteTv(tv: TvEntity)
}