package com.jhorgi.cinematica.core.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhorgi.cinematica.core.databinding.ItemListMovieBinding
import com.jhorgi.cinematica.core.domain.model.Movie

class MovieListViewHolder(
    val binding: ItemListMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Movie){
        with(binding){
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
                .into(ivItemImage)
        }
    }
}