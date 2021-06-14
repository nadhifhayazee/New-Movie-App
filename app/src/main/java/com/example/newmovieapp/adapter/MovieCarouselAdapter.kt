package com.example.newmovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newmovieapp.BuildConfig.BACKDROP_URL
import com.example.newmovieapp.databinding.ItemCarouselLayoutBinding
import com.example.newmovieapp.model.Movie
import io.github.vejei.carouselview.CarouselAdapter

class MovieCarouselAdapter : CarouselAdapter<MovieCarouselAdapter.ViewHolder>() {

    private var dataList = ArrayList<Movie>()

    fun setData(data: ArrayList<Movie>) {
        this.dataList.clear()
        this.dataList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCarouselLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            Glide.with(binding.root)
                .load(BACKDROP_URL + movie?.backdrop_path)
                .into(binding.ivBackdrop)
        }

    }

    override fun onCreatePageViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCarouselLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindPageViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList.getOrNull(position))
    }

    override fun getPageCount(): Int {
        if (dataList.size > 5) return 5
        return dataList.size
    }
}