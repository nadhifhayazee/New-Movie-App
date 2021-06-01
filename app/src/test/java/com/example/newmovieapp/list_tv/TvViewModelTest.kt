package com.example.newmovieapp.list_tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.newmovieapp.list_tv.repository.RemoteTvRepositoryImpl
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.DataDummyHelper
import com.example.newmovieapp.util.LiveDataTestUtil.getOrAwaitValue
import com.example.newmovieapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TvViewModel

    @Mock
    private lateinit var fakeTvRepository: RemoteTvRepositoryImpl


    @Before
    fun setUp() {
        viewModel = TvViewModel(fakeTvRepository)
    }


    @Test
    fun `get popular tvs success`() {
        val dummyPopularTvs = DataDummyHelper.getPopularTvDummies()

        Mockito.`when`(fakeTvRepository.getPopularTv("id-ID")).thenAnswer {
            liveData {
                emit(State.success(dummyPopularTvs))
            }

        }
        val result = viewModel.getPopularTvs().getOrAwaitValue()

        assertNotNull(result.data)
        assertEquals(
            State.success(dummyPopularTvs).data,
            result.data
        )

    }

    @Test
    fun `get popular tvs empty`() {
        Mockito.`when`(fakeTvRepository.getPopularTv("id-ID")).thenAnswer {
            liveData {
                emit(State.error<ArrayList<Movie>>("Tidak Ada Data"))
            }

        }
        val result = viewModel.getPopularTvs().getOrAwaitValue()

        assertNull(result.data)
        assertEquals(
            State.error<ArrayList<Movie>>("Tidak Ada Data").message,
            result.message
        )

    }

    @Test
    fun `get playing tvs success`() {
        val getPlayingTvs = DataDummyHelper.getPlayingTvDummies()

        Mockito.`when`(fakeTvRepository.getNowPlayingTv("id-ID")).thenAnswer {
            liveData {
                emit(State.success(getPlayingTvs))
            }

        }
        val result = viewModel.getNowPlayingTvs().getOrAwaitValue()

        assertNotNull(result.data)
        assertEquals(
            State.success(getPlayingTvs).data,
            result.data
        )

    }

    @Test
    fun `get playing tvs empty`() {
        Mockito.`when`(fakeTvRepository.getNowPlayingTv("id-ID")).thenAnswer {
            liveData {
                emit(State.error<ArrayList<Movie>>("Tidak Ada Data"))
            }

        }
        val result = viewModel.getNowPlayingTvs().getOrAwaitValue()

        assertNull(result.data)
        assertEquals(
            State.error<ArrayList<Movie>>("Tidak Ada Data").message,
            result.message
        )


    }
}