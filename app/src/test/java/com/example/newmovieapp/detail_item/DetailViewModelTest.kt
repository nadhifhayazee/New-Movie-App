package com.example.newmovieapp.detail_item

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.newmovieapp.detail_item.repository.RemoteDetailRepositoryImpl
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.DataDummyHelper
import com.example.newmovieapp.util.LiveDataTestUtil.getOrAwaitValue
import com.example.newmovieapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
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
class DetailViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var fakeDetailRepository: RemoteDetailRepositoryImpl


    @Before
    fun setUp() {
        viewModel = DetailViewModel(fakeDetailRepository)
    }

    @Test
    fun `get detail movie success`() {
        val dummyMovie = DataDummyHelper.getDummyMovie()

        Mockito.`when`(fakeDetailRepository.getDetailMovie("503736", "id-ID")).thenAnswer {
            liveData {
                emit(State.success(dummyMovie))
            }

        }
        val result = viewModel.getDetailMovie("503736").getOrAwaitValue()

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
    fun `get detail tv success`() {
        val dummyTv = DataDummyHelper.getDummyTv()

        Mockito.`when`(fakeDetailRepository.getDetailTv("60735", "id-ID")).thenAnswer {
            liveData {
                emit(State.success(dummyTv))
            }

        }
        val result = viewModel.getDetailTv("60735").getOrAwaitValue()

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