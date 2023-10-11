package com.jhorgi.cinematica.commonAdapter.tvSeriesSeeAllAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class TvSeriesListPagingViewHolder(
    val binding: ItemFavoriteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: TvSeries) {
        data.let {
            binding.ivItemImage.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
        }

        binding.tittleTV.text = data.title
        if (data.overview != "") {
            binding.descTV.text = data.overview
        }
        binding.runTimeTV.text =
            data.firstAirDate?.split("-")?.get(0) ?: "release date not available"
    }
}