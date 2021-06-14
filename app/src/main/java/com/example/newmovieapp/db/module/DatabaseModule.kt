package com.example.newmovieapp.db.module

import android.content.Context
import androidx.room.Room
import com.example.newmovieapp.db.FavoriteDatabase
import com.example.newmovieapp.db.dao.MovieEntityDao
import com.example.newmovieapp.db.dao.TvEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,
        "favorite_db"
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: FavoriteDatabase): MovieEntityDao = db.movieDao()

    @Singleton
    @Provides
    fun provideTvDao(db: FavoriteDatabase): TvEntityDao = db.tvDao()
}