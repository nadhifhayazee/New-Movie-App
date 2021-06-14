package com.example.newmovieapp.list_movie.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.newmovieapp.list_movie.data.RemoteMovieDataSourceImpl
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteMovieRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var fakeMovieDataSource: RemoteMovieDataSourceImpl

    @Mock
    private lateinit var movieRepository: RemoteMovieRepositoryImpl


    @Test
    fun `get popular movies success`() {
        val dummiesPopularMovies = DataDummyHelper.getPopularMovieDummies()
        runBlockingTest {
            `when`(fakeMovieDataSource.getPopularMovies("id-ID")).thenAnswer {
                State.success(MovieResponse(dummiesPopularMovies))
            }

        }

        `when`(movieRepository.getPopularMovies("id-ID")).thenAnswer {
            liveData {
                emit(fakeMovieDataSource.getPopularMovies("id-ID"))
            }
        }
        val result = movieRepository.getPopularMovies("id-ID").getOrAwaitValue()

        Assert.assertNotNull(result.data)
        Assert.assertEquals(
            State.success(MovieResponse(dummiesPopularMovies)).data,
            result.data
        )
    }


    @Test
    fun `get popular movies empty`() {
        runBlockingTest {
            `when`(fakeMovieDataSource.getPopularMovies("id-ID")).thenAnswer {
                State.error<MovieResponse>("Tidak Ada Data")
            }

        }

        `when`(movieRepository.getPopularMovies("id-ID")).thenAnswer {
            liveData {
                emit(fakeMovieDataSource.getPopularMovies("id-ID"))
            }
        }
        val result = movieRepository.getPopularMovies("id-ID").getOrAwaitValue()

        Assert.assertNull(result.data)
        Assert.assertEquals(
            State.error<MovieResponse>("Tidak Ada Data").message,
            result.message
        )
    }

    @Test
    fun `get playing movies success`() {
        val dummiesPlayingMovies = DataDummyHelper.getPlayingMovieDummies()
        runBlockingTest {
            `when`(fakeMovieDataSource.getNowPlayingMovies("id-ID", 1)).thenAnswer {
                State.success(MovieResponse(dummiesPlayingMovies))
            }

        }

        `when`(movieRepository.getNowPlayingMovies("id-ID", 1)).thenAnswer {
            liveData {
                emit(fakeMovieDataSource.getNowPlayingMovies("id-ID", 1))
            }
        }
        val result = movieRepository.getNowPlayingMovies("id-ID", 1).getOrAwaitValue()

        Assert.assertNotNull(result.data)
        Assert.assertEquals(
            State.success(MovieResponse(dummiesPlayingMovies)).data,
            result.data
        )
    }

    @Test
    fun `get playing movies empty`() {
        runBlockingTest {
            `when`(fakeMovieDataSource.getNowPlayingMovies("id-ID", 1)).thenAnswer {
                State.error<MovieResponse>("Tidak Ada Data")
            }

        }

        `when`(movieRepository.getNowPlayingMovies("id-ID", 1)).thenAnswer {
            liveData {
                emit(fakeMovieDataSource.getNowPlayingMovies("id-ID", 1))
            }
        }
        val result = movieRepository.getNowPlayingMovies("id-ID", 1).getOrAwaitValue()

        Assert.assertNull(result.data)
        Assert.assertEquals(
            State.error<MovieResponse>("Tidak Ada Data").message,
            result.message
        )
    }
}