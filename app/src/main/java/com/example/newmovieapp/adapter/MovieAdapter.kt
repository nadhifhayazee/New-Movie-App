package com.example.newmovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovieapp.databinding.ItemMovieLayoutBinding
import com.example.newmovieapp.model.Movie

class MovieAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<PopularMovieViewHolder>() {


    var onItemClick: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMovieViewHolder {

        val binding = ItemMovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PopularMovieViewHolder(binding, onItemClick)

    }


    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bind(movies.getOrNull(position))
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie?, view: View)
    }
}