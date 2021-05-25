package com.example.newmovieapp.adapter

import com.bumptech.glide.Glide
import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.databinding.ItemTrendingMoviesLayoutBinding
import com.example.newmovieapp.model.Movie

class TrendingMovieViewHolder(
    private val binding: ItemTrendingMoviesLayoutBinding,
    private val onItemClick: MovieAdapter.OnItemClickListener?
) : MovieViewHolder(binding.root) {

    override fun bind(movie: Movie?) {
        Glide.with(binding.root)
            .load(BuildConfig.BACKDROP_URL + movie?.backdrop_path)
            .into(binding.ivBackdrop)

        binding.tvTitle.text = movie?.title ?: movie?.name
        binding.root.setOnClickListener {
            onItemClick?.onItemClick(movie, it)
        }
    }
}