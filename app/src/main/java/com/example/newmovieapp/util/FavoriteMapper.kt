package com.example.newmovieapp.util

import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.model.Movie
import com.google.gson.Gson

object FavoriteMapper {

    fun getFavoriteMovies(movieList: List<MovieEntity>) : ArrayList<Movie> {
        val gson = Gson()
        val results = arrayListOf<Movie>()
        if (movieList.isNotEmpty()) {
            movieList.forEach {
                results.add(gson.fromJson(it.data, Movie::class.java))
            }
        }

        return results
    }

    fun getMovie(movie: MovieEntity) : Movie {
        return Gson().fromJson(movie.data, Movie::class.java)
    }

    fun getFavoriteTvs(tvList: List<TvEntity>) : ArrayList<Movie> {
        val gson = Gson()
        val results = arrayListOf<Movie>()
        if (tvList.isNotEmpty()) {
            tvList.forEach {
                results.add(gson.fromJson(it.data, Movie::class.java))
            }
        }

        return results
    }

    fun getTv(tv: TvEntity) : Movie {
        return Gson().fromJson(tv.data, Movie::class.java)
    }

    fun movieToJson(movie: Movie?) : String {
        return Gson().toJson(movie)
    }
}