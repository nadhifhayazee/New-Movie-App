package com.example.newmovieapp.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.newmovieapp.db.entity.TvEntity

@Dao
interface TvEntityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tv: TvEntity)

    @Delete
    suspend fun delete(tv: TvEntity)

    @Query("SELECT * FROM favorite_tv WHERE tv_id = :tvId")
    suspend fun getTv(tvId: Int?): TvEntity?


    @Query("SELECT * FROM favorite_tv ORDER BY id DESC")
    fun getAll(): DataSource.Factory<Int, TvEntity>
}