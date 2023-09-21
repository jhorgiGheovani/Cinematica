package com.jhorgi.cinematica.common.movieListAdapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.utils.DataMapper
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class MovieSearchPageViewHolder(
    private val binding: ItemListCommonBinding,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Movie) {
        data.let {
            binding.PosterIV.load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
        }

        binding.tittleTV.text = data.title

        val convertGenre = DataMapper.getMovieGenres(data.genres)
        binding.genres.text = convertGenre.joinToString (", ")
        itemView.setOnClickListener { onItemClick(data) }
    }


}