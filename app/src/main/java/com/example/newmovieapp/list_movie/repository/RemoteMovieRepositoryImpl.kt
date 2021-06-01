package com.example.newmovieapp.list_movie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.newmovieapp.list_movie.data.RemoteMovieDataSource
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.RequestStatus
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.EspressoIdlingResource
import javax.inject.Inject

class RemoteMovieRepositoryImpl @Inject constructor
    (private val remoteMovieDataSource: RemoteMovieDataSource) :
    MovieRepository {


    override fun getNowPlayingMovies(
        language: String,
        page: Int
    ): LiveData<State<ArrayList<Movie>>> = liveData {
        EspressoIdlingResource.increment()
        val loadingState: State<ArrayList<Movie>> = State.loading()
        val errorState: State<ArrayList<Movie>>
        emit(loadingState)
        val response =
            remoteMovieDataSource.getNowPlayingMovies(language, page)
        when (response.requestStatus) {
            RequestStatus.ERROR -> {
                errorState = State.error(response.message ?: "")
                emit(errorState)
            }
            else -> {
                emit(State.success(response.data?.results))
            }
        }
        EspressoIdlingResource.decrement()

    }

    override fun getPopularMovies(language: String): LiveData<State<ArrayList<Movie>>> = liveData {
        EspressoIdlingResource.increment()
        val loadingState: State<ArrayList<Movie>> = State.loading()
        val errorState: State<ArrayList<Movie>>
        emit(loadingState)
        val response =
            remoteMovieDataSource.getPopularMovies(language)
        when (response.requestStatus) {
            RequestStatus.ERROR -> {
                errorState = State.error(response.message ?: "")
                emit(errorState)
            }
            else -> {
                emit(State.success(response.data?.results))
            }
        }
        EspressoIdlingResource.decrement()

    }
}
