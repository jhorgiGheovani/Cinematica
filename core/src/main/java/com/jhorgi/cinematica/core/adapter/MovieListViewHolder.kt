package com.jhorgi.cinematica.core.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.databinding.ItemListMovieBinding

class MovieListViewHolder(
    val binding: ItemListMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(path: String){
        path.let {
            binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${path}")
        }
    }
}