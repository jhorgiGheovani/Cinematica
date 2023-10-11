package com.jhorgi.cinematica.searchPage.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class SearchResultViewHolder(
    private val binding: ItemFavoriteBinding,
    private val onItemClick: (Movie) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Movie){


        if(data.posterPath!=null){
            data.let{
                binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
            }
        }else{
            binding.ivItemImage.setImageResource(R.drawable.emty_poster_images)
        }


        binding.tittleTV.text = data.title
        if(data.overview!=""){
            binding.descTV.text = data.overview
        }
        binding.runTimeTV.text = data.releaseDate.split("-")?.get(0) ?: "no release date"

//        val genre = data.genres?.map { it. }

        binding.genres.text = data.genres?.joinToString (", ")
        itemView.setOnClickListener { onItemClick(data) }
    }
}