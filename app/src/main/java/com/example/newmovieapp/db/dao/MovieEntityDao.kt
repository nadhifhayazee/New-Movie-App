package com.example.newmovieapp.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.newmovieapp.db.entity.MovieEntity

@Dao
interface MovieEntityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("SELECT * FROM favorite_movie WHERE movie_id = :movieId")
    suspend fun getMovie(movieId: Int?): MovieEntity?

    @Query("SELECT * FROM favorite_movie ORDER BY id DESC")
    fun getAll(): DataSource.Factory<Int, MovieEntity>
}