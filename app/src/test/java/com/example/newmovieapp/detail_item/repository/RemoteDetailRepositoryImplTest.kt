package com.example.newmovieapp.detail_item.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.newmovieapp.detail_item.data.RemoteDetailDataSourceImpl
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.DataDummyHelper
import com.example.newmovieapp.util.LiveDataTestUtil.getOrAwaitValue
import com.example.newmovieapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDetailRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var fakeDetailDataSource: RemoteDetailDataSourceImpl

    @Mock
    private lateinit var detailRepository: RemoteDetailRepositoryImpl

    @Test
    fun getDetailMovieSuccess() = runBlockingTest {
        val dummyMovie = DataDummyHelper.getDummyMovie()

        `when`(fakeDetailDataSource.getDetailMovie(dummyMovie.id.toString(), "id-ID")).thenAnswer {
            State.success(dummyMovie)
        }

        `when`(detailRepository.getDetailMovie(dummyMovie.id.toString(), "id-ID")).thenAnswer {
            liveData {
                emit(fakeDetailDataSource.getDetailMovie(dummyMovie.id.toString(), "id-ID"))
            }
        }

        val result =
            detailRepository.getDetailMovie(dummyMovie.id.toString(), "id-ID").getOrAwaitValue()

        Assert.assertNotNull(result.data)
        Assert.assertEquals(dummyMovie.id, result.data?.id)
        Assert.assertEquals(dummyMovie.title, result.data?.title)
        Assert.assertEquals(
            "",
            result.data?.overview
        )
        Assert.assertEquals(dummyMovie.vote_average, result.data?.vote_average)
        Assert.assertEquals(dummyMovie.runtime, result.data?.runtime)
        Assert.assertEquals(dummyMovie.getGenres(), result.data?.getGenres())
        Assert.assertEquals(dummyMovie.backdrop_path, result.data?.backdrop_path)
        Assert.assertEquals(dummyMovie.poster_path, result.data?.poster_path)

    }

    @Test
    fun getDetailTvSuccess() = runBlockingTest {
        val dummyTv = DataDummyHelper.getDummyTv()

        `when`(fakeDetailDataSource.getDetailTv(dummyTv.id.toString(), "id-ID")).thenAnswer {
            State.success(dummyTv)
        }

        `when`(detailRepository.getDetailTv(dummyTv.id.toString(), "id-ID")).thenAnswer {
            liveData {
                emit(fakeDetailDataSource.getDetailTv(dummyTv.id.toString(), "id-ID"))
            }
        }

        val result = detailRepository.getDetailTv(dummyTv.id.toString(), "id-ID").getOrAwaitValue()
        Assert.assertNotNull(result.data)
        Assert.assertEquals(dummyTv.id, result.data?.id)
        Assert.assertEquals(dummyTv.title, result.data?.title)
        Assert.assertEquals(
            dummyTv.overview,
            result.data?.overview
        )
        Assert.assertEquals(dummyTv.vote_average, result.data?.vote_average)
        Assert.assertEquals(dummyTv.runtime, result.data?.runtime)
        Assert.assertEquals(dummyTv.getGenres(), result.data?.getGenres())
        Assert.assertEquals(dummyTv.backdrop_path, result.data?.backdrop_path)
        Assert.assertEquals(dummyTv.poster_path, result.data?.poster_path)

    }

}