package com.example.newmovieapp.favorite.data

import androidx.paging.DataSource
import com.example.newmovieapp.db.dao.MovieEntityDao
import com.example.newmovieapp.db.dao.TvEntityDao
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import javax.inject.Inject

class LocalFavoriteDataSourceImpl @Inject constructor(
    private val movieDao: MovieEntityDao,
    private val tvDao: TvEntityDao
) : LocalFavoriteDataSource {
    override fun getAllMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getAll()
    }

    override suspend fun getMovie(movieId: Int): MovieEntity? {
        return movieDao.getMovie(movieId)
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        return movieDao.insert(movie)
    }

    override suspend fun deleteMovie(movie: MovieEntity) {
        movieDao.delete(movie)
    }

    override fun getAllTvs(): DataSource.Factory<Int, TvEntity> {
        return tvDao.getAll()
    }

    override suspend fun getTv(tvId: Int): TvEntity? {
        return tvDao.getTv(tvId)
    }

    override suspend fun insertTv(tv: TvEntity) {
        return tvDao.insert(tv)
    }

    override suspend fun deleteTv(tv: TvEntity) {
        return tvDao.delete(tv)
    }

}