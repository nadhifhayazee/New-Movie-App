package com.example.newmovieapp.list_movie.module

import com.example.newmovieapp.list_movie.data.RemoteMovieDataSource
import com.example.newmovieapp.list_movie.data.RemoteMovieDataSourceImpl
import com.example.newmovieapp.list_movie.repository.MovieRepository
import com.example.newmovieapp.list_movie.repository.RemoteMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteMovieModule {

    @Singleton
    @Provides
    fun provideRemoteMovieDataSource(remoteMovieDataSource: RemoteMovieDataSourceImpl): RemoteMovieDataSource =
        remoteMovieDataSource

    @Singleton
    @Provides
    fun provideRemoteMovieRepository(remoteMovieRepositoryImpl: RemoteMovieRepositoryImpl): MovieRepository =
        remoteMovieRepositoryImpl
}