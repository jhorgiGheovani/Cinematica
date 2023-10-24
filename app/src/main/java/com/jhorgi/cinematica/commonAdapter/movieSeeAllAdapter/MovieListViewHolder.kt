package com.jhorgi.cinematica.commonAdapter.movieSeeAllAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class MovieListViewHolder(
    val binding: ItemFavoriteBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Movie){
        data.let {
            binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
        }

        binding.tittleTV.text = data.title
        if(data.overview!=""){
            binding.descTV.text = data.overview
        }
        binding.runTimeTV.text = data.releaseDate.split("-")[0]

        binding.genres.text = data.genres.joinToString (", ")

        binding.textStart.text = data.rating.toString()
    }
}