package com.jhorgi.cinematica.common.movieSeeAllAdapter

import androidx.recyclerview.widget.DiffUtil
import com.jhorgi.cinematica.core.domain.model.Movie

class MovieDiffCallBack: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}