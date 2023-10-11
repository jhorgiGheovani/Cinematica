package com.jhorgi.cinematica.commonAdapter.tvSeriesListAdapterV1

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.databinding.ItemListStyle1Binding

class TvSeriesListViewHolderV1(
    private val binding: ItemListStyle1Binding,
    private val onItemClick: (TvSeries) -> Unit
): RecyclerView.ViewHolder(binding.root)   {
    fun bind(data: TvSeries) {

        if (data.posterPath != null) {
            data.let {
                binding.PosterIV.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
            }
        } else {
            binding.PosterIV.setImageResource(R.drawable.emty_poster_images)
        }


        binding.tittleTV.text = data.title
        binding.genres.text = data.genres?.joinToString (", ")
        itemView.setOnClickListener { onItemClick(data) }
    }
}