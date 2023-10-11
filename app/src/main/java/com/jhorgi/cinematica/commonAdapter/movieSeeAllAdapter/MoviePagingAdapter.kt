package com.jhorgi.cinematica.commonAdapter.movieSeeAllAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class MoviePagingAdapter:
    PagingDataAdapter<Movie, MovieListViewHolder>(MovieDiffCallBack()) {

    var onItemClick: ((Movie)-> Unit)? = null

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val holder = MovieListViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { movie ->
                    onItemClick?.invoke(movie)
                }
            }

        }

        return holder
    }
}