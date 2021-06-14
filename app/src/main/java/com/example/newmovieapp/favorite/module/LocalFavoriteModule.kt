package com.example.newmovieapp.favorite.module

import com.example.newmovieapp.favorite.data.LocalFavoriteDataSource
import com.example.newmovieapp.favorite.data.LocalFavoriteDataSourceImpl
import com.example.newmovieapp.favorite.repository.LocalFavoriteRepository
import com.example.newmovieapp.favorite.repository.LocalFavoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalFavoriteModule {

    @Singleton
    @Provides
    fun provideFavoriteDataSource(favoriteDataSource: LocalFavoriteDataSourceImpl): LocalFavoriteDataSource = favoriteDataSource

    @Singleton
    @Provides
    fun provideFavoriteRepository(favoriteRepository: LocalFavoriteRepositoryImpl): LocalFavoriteRepository = favoriteRepository

}