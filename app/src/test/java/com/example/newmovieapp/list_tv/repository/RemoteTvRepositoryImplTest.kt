package com.example.newmovieapp.list_tv.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.newmovieapp.list_tv.data.RemoteTvDataSourceImpl
import com.example.newmovieapp.model.MovieResponse
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteTvRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var fakeTvDataSource: RemoteTvDataSourceImpl

    @Mock
    private lateinit var tvRepository: RemoteTvRepositoryImpl

    @Test
    fun `get popular tv success`() = runBlockingTest {
        val dummiesPopularTvs = DataDummyHelper.getPopularTvDummies()

        Mockito.`when`(fakeTvDataSource.getPopularTv("id-ID")).thenAnswer {
            State.success(MovieResponse(dummiesPopularTvs))
        }



        Mockito.`when`(tvRepository.getPopularTv("id-ID")).thenAnswer {
            liveData {
                emit(fakeTvDataSource.getPopularTv("id-ID"))
            }
        }

        val result = tvRepository.getPopularTv("id-ID").getOrAwaitValue()

        Assert.assertNotNull(result.data)
        Assert.assertEquals(
            State.success(MovieResponse(dummiesPopularTvs)).data,
            result.data
        )
    }


    @Test
    fun `get popular tv empty`() {
        runBlockingTest {
            Mockito.`when`(fakeTvDataSource.getPopularTv("id-ID")).thenAnswer {
                State.error<MovieResponse>("Tidak Ada Data")
            }

        }

        Mockito.`when`(tvRepository.getPopularTv("id-ID")).thenAnswer {
            liveData {
                emit(fakeTvDataSource.getPopularTv("id-ID"))
            }
        }
        val result = tvRepository.getPopularTv("id-ID").getOrAwaitValue()

        Assert.assertNull(result.data)
        Assert.assertEquals(
            State.error<MovieResponse>("Tidak Ada Data").message,
            result.message
        )
    }


    @Test
    fun `get playing tv success`() {
        val dummiesPlayingTvs = DataDummyHelper.getPlayingTvDummies()
        runBlockingTest {
            Mockito.`when`(fakeTvDataSource.getNowPlayingTv("id-ID")).thenAnswer {
                State.success(MovieResponse(dummiesPlayingTvs))
            }

        }

        Mockito.`when`(tvRepository.getNowPlayingTv("id-ID")).thenAnswer {
            liveData {
                emit(fakeTvDataSource.getNowPlayingTv("id-ID"))
            }
        }
        val result = tvRepository.getNowPlayingTv("id-ID").getOrAwaitValue()

        Assert.assertNotNull(result.data)
        Assert.assertEquals(
            State.success(MovieResponse(dummiesPlayingTvs)).data,
            result.data
        )
    }

    @Test
    fun `get playing tv empty`() {
        runBlockingTest {
            Mockito.`when`(fakeTvDataSource.getNowPlayingTv("id-ID")).thenAnswer {
                State.error<MovieResponse>("Tidak Ada Data")
            }

        }

        Mockito.`when`(tvRepository.getNowPlayingTv("id-ID")).thenAnswer {
            liveData {
                emit(fakeTvDataSource.getNowPlayingTv("id-ID"))
            }
        }
        val result = tvRepository.getNowPlayingTv("id-ID").getOrAwaitValue()

        Assert.assertNull(result.data)
        Assert.assertEquals(
            State.error<MovieResponse>("Tidak Ada Data").message,
            result.message
        )
    }

}