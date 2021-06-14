package com.example.newmovieapp.network

import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") api_key: String?, @Query("language") language: String?,
        @Query("page") page: Int
    ): Response<MovieResponse>


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String?, @Query("language") language: String?
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id: String?,
        @Query("api_key") api_key: String?,
        @Query("language") language: String?
    ): Response<Movie>

//    TV

    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") api_key: String?, @Query("language") language: String?
    ): Response<MovieResponse>


    @GET("tv/on_the_air")
    suspend fun getNowPlayingTv(
        @Query("api_key") api_key: String?, @Query("language") language: String?
    ): Response<MovieResponse>

    @GET("tv/{tv_id}")
    suspend fun getDetailTv(
        @Path("tv_id") tv_id: String?,
        @Query("api_key") api_key: String?,
        @Query("language") language: String?
    ): Response<Movie>
}