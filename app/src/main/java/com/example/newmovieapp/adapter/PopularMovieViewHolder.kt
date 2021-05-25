package com.example.newmovieapp.adapter

import com.bumptech.glide.Glide
import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.databinding.ItemMovieLayoutBinding
import com.example.newmovieapp.model.Movie

class PopularMovieViewHolder(
    private val binding: ItemMovieLayoutBinding,
    private val onItemClick: MovieAdapter.OnItemClickListener?
) : MovieViewHolder(binding.root) {

    override fun bind(movie: Movie?) {
        Glide.with(binding.root)
            .load(BuildConfig.BACKDROP_URL + movie?.poster_path)
            .into(binding.ivBackdrop)

        binding.tvTitle.text = movie?.title ?: movie?.name
        binding.root.setOnClickListener {
            onItemClick?.onItemClick(movie ,it)
        }
    }
}