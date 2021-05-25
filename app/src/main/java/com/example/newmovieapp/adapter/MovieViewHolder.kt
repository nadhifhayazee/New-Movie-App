package com.example.newmovieapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovieapp.model.Movie

abstract class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(movie: Movie?)
}