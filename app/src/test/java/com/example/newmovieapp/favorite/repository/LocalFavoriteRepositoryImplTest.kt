package com.example.newmovieapp.favorite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.favorite.data.LocalFavoriteDataSourceImpl
import com.example.newmovieapp.util.DataDummyHelper
import com.example.newmovieapp.util.PagedListUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalFavoriteRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fakeLocalDataSource: LocalFavoriteDataSourceImpl

    private lateinit var favoriteRepositoryImpl: LocalFavoriteRepositoryImpl

    private val dummyFavoriteMovie = DataDummyHelper.getDummyFavoriteMovie()

    private val dummyFavoriteTv = DataDummyHelper.getDummyFavoriteTv()


    @Before
    fun setUp() {
        favoriteRepositoryImpl = LocalFavoriteRepositoryImpl(fakeLocalDataSource)
    }


    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(fakeLocalDataSource.getAllMovies()).thenReturn(dataSourceFactory)

        favoriteRepositoryImpl.getAllMovie()

        val movieEntities = PagedListUtil.mockPagedList(dummyFavoriteMovie)
        verify(fakeLocalDataSource).getAllMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(dummyFavoriteMovie.size.toLong(), movieEntities.size.toLong())


    }

    @Test
    fun getAllTvs() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(fakeLocalDataSource.getAllTvs()).thenReturn(dataSourceFactory)

        favoriteRepositoryImpl.getAllTv()

        val tvEntities = PagedListUtil.mockPagedList(dummyFavoriteTv)
        verify(fakeLocalDataSource).getAllTvs()
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(dummyFavoriteTv.size.toLong(), tvEntities.size.toLong())

    }

}