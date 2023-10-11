package com.jhorgi.cinematica.commonAdapter.movieListAdapterV1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class MovieListAdapterV1(
    private val list: List<Movie>,
    private val onItemClick: (Movie) ->Unit
): RecyclerView.Adapter<MovieListViewHolderV1>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolderV1 {
        return MovieListViewHolderV1(
            ItemListCommonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: MovieListViewHolderV1, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}