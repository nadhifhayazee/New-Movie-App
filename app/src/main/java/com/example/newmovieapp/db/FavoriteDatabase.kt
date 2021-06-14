package com.example.newmovieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newmovieapp.db.dao.MovieEntityDao
import com.example.newmovieapp.db.dao.TvEntityDao
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieEntityDao
    abstract fun tvDao(): TvEntityDao


}