package com.example.newmovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.newmovieapp.databinding.ItemMovieLayoutBinding
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.util.FavoriteMapper

class FavoriteMovieAdapter : PagedListAdapter<MovieEntity, PopularMovieViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movie_id == newItem.movie_id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    var onItemClick: MovieAdapter.OnItemClickListener? = null

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


    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        getItem(position)?.let {
            val movie = FavoriteMapper.getMovie(it)
            holder.bind(movie)
        }
    }

}