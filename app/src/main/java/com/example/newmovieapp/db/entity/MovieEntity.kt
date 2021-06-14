package com.example.newmovieapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "movie_id")
    var movie_id: Int? = null,

    @ColumnInfo(name = "data")
    var data: String? = null
        )