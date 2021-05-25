package com.example.newmovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovieapp.databinding.ItemMovieLayoutBinding
import com.example.newmovieapp.databinding.ItemTrendingMoviesLayoutBinding
import com.example.newmovieapp.model.Movie

class MovieAdapter(private val movieType: MovieType) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = ArrayList<Movie>()
    var onItemClick: OnItemClickListener? = null

    fun setData(data: ArrayList<Movie>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return movieType.value
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        return when (viewType) {
            MovieType.POPULAR.value -> {
                val binding = ItemMovieLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                PopularMovieViewHolder(binding, onItemClick)

            }

            else -> {
                val binding = ItemTrendingMoviesLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TrendingMovieViewHolder(binding, onItemClick)
            }
        }


    }


    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.getOrNull(position))
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie?, view: View)
    }
}