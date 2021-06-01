package com.example.newmovieapp.network

class State<out T>(
    val requestStatus: RequestStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): State<T> {
            return State(
                RequestStatus.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String): State<T> {
            return State(
                RequestStatus.ERROR,
                null,
                msg
            )
        }

        fun <T> loading(): State<T> {
            return State(
                RequestStatus.LOADING,
                null,
                null
            )
        }
    }
}