package com.example.newmovieapp.list_tv.module

import com.example.newmovieapp.list_tv.data.RemoteTvDataSource
import com.example.newmovieapp.list_tv.data.RemoteTvDataSourceImpl
import com.example.newmovieapp.list_tv.repository.RemoteTvRepositoryImpl
import com.example.newmovieapp.list_tv.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteTvModule {

    @Singleton
    @Provides
    fun provideRemoteTvDataSource(remoteTvDataSource: RemoteTvDataSourceImpl): RemoteTvDataSource =
        remoteTvDataSource

    @Singleton
    @Provides
    fun provideRemoteTvRepository(remoteTvRepositoryImpl: RemoteTvRepositoryImpl): TvRepository =
        remoteTvRepositoryImpl
}