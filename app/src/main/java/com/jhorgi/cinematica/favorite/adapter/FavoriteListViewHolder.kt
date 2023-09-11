package com.jhorgi.cinematica.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.domain.model.FavoriteMovie
import com.jhorgi.cinematica.databinding.ItemFavoriteMovieBinding

class FavoriteListViewHolder(
    private val binding: ItemFavoriteMovieBinding
): RecyclerView.ViewHolder(binding.root)  {

    fun bind(data: FavoriteMovie){
        data.let {
            binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
        }

        binding.tittleTV.text = data.title
        binding.descTV.text = data.overview
        binding.runTimeTV.text = data.releaseDate.split("-")[0]

        val genre = data.genres.map { it.name }

        binding.genres.text = genre.joinToString (", ")
    }
}