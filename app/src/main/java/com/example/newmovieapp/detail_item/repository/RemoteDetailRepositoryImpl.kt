package com.example.newmovieapp.detail_item.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.newmovieapp.detail_item.data.RemoteDetailDataSource
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.RequestStatus
import com.example.newmovieapp.network.State
import com.example.newmovieapp.util.EspressoIdlingResource
import javax.inject.Inject

class RemoteDetailRepositoryImpl @Inject constructor(
    private val detailDataSource: RemoteDetailDataSource
) : DetailRepository {
    override fun getDetailMovie(movieId: String, language: String): LiveData<State<Movie>> =
        liveData {
            EspressoIdlingResource.increment()
            val loadingState: State<Movie> = State.loading()
            val errorState: State<Movie>
            emit(loadingState)
            val response = detailDataSource.getDetailMovie(movieId, language)
            when (response.requestStatus) {
                RequestStatus.ERROR -> {
                    errorState = State.error(response.message ?: "")
                    emit(errorState)
                }
                else -> {
                    emit(State.success(response.data))
                }
            }
            EspressoIdlingResource.decrement()
        }

    override fun getDetailTv(tvId: String, language: String): LiveData<State<Movie>> = liveData {
        EspressoIdlingResource.increment()
        val loadingState: State<Movie> = State.loading()
        val errorState: State<Movie>
        emit(loadingState)
        val response = detailDataSource.getDetailTv(tvId, language)
        when (response.requestStatus) {
            RequestStatus.ERROR -> {
                errorState = State.error(response.message ?: "")
                emit(errorState)
            }
            else -> {
                emit(State.success(response.data))
            }
        }
        EspressoIdlingResource.decrement()
    }
}