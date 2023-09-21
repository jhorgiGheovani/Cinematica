package com.jhorgi.cinematica.common.tvListAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.utils.DataMapper
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class TvListSearchPageViewHolder(
    private val binding: ItemListCommonBinding,
    private val onItemClick: (TvSeries) -> Unit
)
    : RecyclerView.ViewHolder(binding.root){

        fun bind(data: TvSeries){
            data.let {
                binding.PosterIV.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
            }

            binding.tittleTV.text = data.originalTitle

            val convertGenre = DataMapper.getMovieGenres(data.genres!!)
            binding.genres.text = convertGenre.joinToString (", ")
            itemView.setOnClickListener { onItemClick(data) }
        }
}