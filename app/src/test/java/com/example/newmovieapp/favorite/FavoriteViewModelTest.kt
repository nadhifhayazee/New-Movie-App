package com.example.newmovieapp.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.paging.PagedList
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.favorite.helper.InsertResponse
import com.example.newmovieapp.favorite.repository.LocalFavoriteRepositoryImpl
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.DataDummyHelper
import com.example.newmovieapp.util.LiveDataTestUtil.getOrAwaitValue
import com.example.newmovieapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvEntity>>


    @Mock
    private lateinit var pageListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pageListTv: PagedList<TvEntity>


    @Mock
    private lateinit var fakeFavoriteRepositoryImpl: LocalFavoriteRepositoryImpl


    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(fakeFavoriteRepositoryImpl)
    }

    @Test
    fun `get favorite movies success`() {
        val dummyFav = pageListMovie
        `when`(dummyFav.size).thenReturn(5)
        val favMovies = MutableLiveData<PagedList<MovieEntity>>()

        favMovies.value = dummyFav
        `when`(fakeFavoriteRepositoryImpl.getAllMovie()).thenReturn(favMovies)

        val movieEntities = favoriteViewModel.getMovies().value

        verify(fakeFavoriteRepositoryImpl).getAllMovie()

        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)
        favoriteViewModel.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyFav)

    }

    @Test
    fun `get favorite tv success`() {
        val dummyFav = pageListTv
        `when`(dummyFav.size).thenReturn(5)
        val favTvs = MutableLiveData<PagedList<TvEntity>>()

        favTvs.value = dummyFav
        `when`(fakeFavoriteRepositoryImpl.getAllTv()).thenReturn(favTvs)

        val tvEntities = favoriteViewModel.getTvs().value

        verify(fakeFavoriteRepositoryImpl).getAllTv()

        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)
        favoriteViewModel.getTvs().observeForever(observerTv)
        verify(observerTv).onChanged(dummyFav)

    }


    @Test
    fun `insert favorite movie`() {
        val movieDummy = DataDummyHelper.dummyMovieEntity()
        `when`(fakeFavoriteRepositoryImpl.insertMovie(movieDummy)).thenAnswer {
            liveData {
                emit(State.success(InsertResponse.INSERTED))
            }
        }

        val result = favoriteViewModel.insertMovie(movieDummy).getOrAwaitValue()

        assertEquals(result.data, InsertResponse.INSERTED)
    }

    @Test
    fun `delete favorite movie`() {
        val movieDummy = DataDummyHelper.dummyMovieEntity()
        `when`(fakeFavoriteRepositoryImpl.insertMovie(movieDummy)).thenAnswer {
            liveData {
                emit(State.success(InsertResponse.DELETED))
            }
        }

        val result = favoriteViewModel.insertMovie(movieDummy).getOrAwaitValue()

        assertEquals(result.data, InsertResponse.DELETED)
    }

    @Test
    fun `insert favorite tv`() {
        val tvDummy = DataDummyHelper.dummyTvEntity()
        `when`(fakeFavoriteRepositoryImpl.insertTv(tvDummy)).thenAnswer {
            liveData {
                emit(State.success(InsertResponse.INSERTED))
            }
        }

        val result = favoriteViewModel.insertTv(tvDummy).getOrAwaitValue()

        assertEquals(result.data, InsertResponse.INSERTED)
    }

    @Test
    fun `delete favorite tv`() {
        val tvDummy = DataDummyHelper.dummyTvEntity()
        `when`(fakeFavoriteRepositoryImpl.insertTv(tvDummy)).thenAnswer {
            liveData {
                emit(State.success(InsertResponse.DELETED))
            }
        }

        val result = favoriteViewModel.insertTv(tvDummy).getOrAwaitValue()

        assertEquals(result.data, InsertResponse.DELETED)
    }

    @Test
    fun `check is favorite movie`() {
        `when`(fakeFavoriteRepositoryImpl.isFavoriteMovie(63314)).thenAnswer {
            liveData {
                emit(State.success(true))
            }
        }

        val result = favoriteViewModel.checkIsFavoriteMovie(63314).getOrAwaitValue()

        assertEquals(result.data, true)

    }

    @Test
    fun `check is not favorite movie`() {
        `when`(fakeFavoriteRepositoryImpl.isFavoriteMovie(63314)).thenAnswer {
            liveData {
                emit(State.success(false))
            }
        }

        val result = favoriteViewModel.checkIsFavoriteMovie(63314).getOrAwaitValue()

        assertEquals(result.data, false)

    }

    @Test
    fun `check is favorite tv`() {
        `when`(fakeFavoriteRepositoryImpl.isFavoriteTv(63314)).thenAnswer {
            liveData {
                emit(State.success(true))
            }
        }

        val result = favoriteViewModel.checkIsFavoriteTv(63314).getOrAwaitValue()

        assertEquals(result.data, true)

    }


    @Test
    fun `check is not favorite tv`() {
        `when`(fakeFavoriteRepositoryImpl.isFavoriteTv(63314)).thenAnswer {
            liveData {
                emit(State.success(false))
            }
        }

        val result = favoriteViewModel.checkIsFavoriteTv(63314).getOrAwaitValue()

        assertEquals(result.data, false)

    }
}