package com.jhorgi.cinematica.commonAdapter.listAdapterListV2

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList2
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class ListViewHolderV2(
    private val binding: ItemFavoriteBinding,
    private val onItemClick: (RecyclerViewDataList2)-> Unit
):RecyclerView.ViewHolder(binding.root) {

    fun bind(data: RecyclerViewDataList2){


        if(data.posterPath!=null){
            data.let{
                binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
            }
        }else{
            binding.ivItemImage.setImageResource(R.drawable.emty_poster_images)
        }


        binding.tittleTV.text = data.tittle
        if(data.overview!=""){
            binding.descTV.text = data.overview
        }

            binding.runTimeTV.text = data.releaseDate?.split("-")?.get(0) ?: "no release date"




        binding.genres.text = data.genres?.joinToString (", ")
        binding.textStart.text = data.rating
        itemView.setOnClickListener { onItemClick(data) }
    }
}