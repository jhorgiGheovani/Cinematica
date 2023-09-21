package com.jhorgi.cinematica.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class FavoriteListViewHolder(
    private val binding: ItemFavoriteBinding,
    private val onItemClick: (FavoriteItem) -> Unit
): RecyclerView.ViewHolder(binding.root)  {

    fun bind(data: FavoriteItem){
        data.let {
            binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
        }

        binding.tittleTV.text = data.title
        if(data.overview!=""){
            binding.descTV.text = data.overview
        }
        binding.runTimeTV.text = data.releaseDate?.split("-")?.get(0) ?: "no release date"

        val genre = data.genres?.map { it.name }

        binding.genres.text = genre?.joinToString (", ")
        itemView.setOnClickListener { onItemClick(data) }
    }
}