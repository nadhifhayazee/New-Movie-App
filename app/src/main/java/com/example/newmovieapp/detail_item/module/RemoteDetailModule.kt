package com.example.newmovieapp.detail_item.module

import com.example.newmovieapp.detail_item.data.RemoteDetailDataSource
import com.example.newmovieapp.detail_item.data.RemoteDetailDataSourceImpl
import com.example.newmovieapp.detail_item.repository.DetailRepository
import com.example.newmovieapp.detail_item.repository.RemoteDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDetailModule {

    @Singleton
    @Provides
    fun provideRemoteDetailDataSource(remoteDetailDataSource: RemoteDetailDataSourceImpl): RemoteDetailDataSource =
        remoteDetailDataSource

    @Singleton
    @Provides
    fun provideRemoteDetailRepository(remoteDetailRepositoryImpl: RemoteDetailRepositoryImpl): DetailRepository =
        remoteDetailRepositoryImpl
}