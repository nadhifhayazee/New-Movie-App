package com.example.newmovieapp.list_movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.newmovieapp.list_movie.repository.RemoteMovieRepositoryImpl
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var fakeMovieRepository: RemoteMovieRepositoryImpl


    @Before
    fun setUp() {
        viewModel = MovieViewModel(fakeMovieRepository)
    }


    @Test
    fun `get popular movies success`() {
        val dummyPopularMovies = DataDummyHelper.getPopularMovieDummies()

        `when`(fakeMovieRepository.getPopularMovies("id-ID")).thenAnswer {
            liveData {
                emit(State.success(dummyPopularMovies))
            }

        }
        val result = viewModel.getPopularMovies().getOrAwaitValue()

        assertNotNull(result.data)
        assertEquals(
            State.success(dummyPopularMovies).data,
            result.data
        )

    }

    @Test
    fun `get popular movies empty`() {
        `when`(fakeMovieRepository.getPopularMovies("id-ID")).thenAnswer {
            liveData {
                emit(State.error<ArrayList<Movie>>("Tidak Ada Data"))
            }

        }
        val result = viewModel.getPopularMovies().getOrAwaitValue()

        assertNull(result.data)
        assertEquals(
            State.error<ArrayList<Movie>>("Tidak Ada Data").message,
            result.message
        )

    }

    @Test
    fun `get playing movies success`() {
        val dummyPopularMovies = DataDummyHelper.getPlayingMovieDummies()

        `when`(fakeMovieRepository.getNowPlayingMovies("id-ID", 1)).thenAnswer {
            liveData {
                emit(State.success(dummyPopularMovies))
            }

        }
        val result = viewModel.getNowPlayingMovies().getOrAwaitValue()

        assertNotNull(result.data)
        assertEquals(
            State.success(dummyPopularMovies).data,
            result.data
        )

    }

    @Test
    fun `get playing movies empty`() {
        `when`(fakeMovieRepository.getNowPlayingMovies("id-ID", 1)).thenAnswer {
            liveData {
                emit(State.error<ArrayList<Movie>>("Tidak Ada Data"))
            }

        }
        val result = viewModel.getNowPlayingMovies().getOrAwaitValue()

        assertNull(result.data)
        assertEquals(
            State.error<ArrayList<Movie>>("Tidak Ada Data").message,
            result.message
        )


    }


}