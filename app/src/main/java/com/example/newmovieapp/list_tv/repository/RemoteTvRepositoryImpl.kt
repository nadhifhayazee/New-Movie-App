package com.example.newmovieapp.list_tv.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.newmovieapp.list_tv.data.RemoteTvDataSource
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.RequestStatus
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.EspressoIdlingResource
import javax.inject.Inject

class RemoteTvRepositoryImpl @Inject constructor
    (private val remoteTvDataSource: RemoteTvDataSource) :
    TvRepository {

    override fun getNowPlayingTv(language: String): LiveData<State<ArrayList<Movie>>> = liveData {
        EspressoIdlingResource.increment()
        val loadingState: State<ArrayList<Movie>> = State.loading()
        val errorState: State<ArrayList<Movie>>
        emit(loadingState)
        val response =
            remoteTvDataSource.getNowPlayingTv(language)
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

    override fun getPopularTv(language: String): LiveData<State<ArrayList<Movie>>> = liveData {
        EspressoIdlingResource.increment()
        val loadingState: State<ArrayList<Movie>> = State.loading()
        val errorState: State<ArrayList<Movie>>
        emit(loadingState)
        val response =
            remoteTvDataSource.getPopularTv(language)
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
