package com.jhorgi.cinematica.commonAdapter.listAdapterV1

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList1
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class ListViewHolderV1(
    private val binding: ItemListCommonBinding,
    private val onItemClick: (RecyclerViewDataList1) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: RecyclerViewDataList1) {
        if (data.posterPath != null) {
            data.let {
                binding.PosterIV.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
            }
        } else {

            binding.PosterIV.setImageResource(R.drawable.emty_poster_images)
        }


        binding.tittleTV.text = data.tittle
        binding.genres.text = data.genres?.joinToString (", ")

        itemView.setOnClickListener { onItemClick(data) }
    }
}