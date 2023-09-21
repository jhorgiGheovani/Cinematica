package com.jhorgi.cinematica.common.movieListAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class MovieAdapter(
    private val list: List<Movie>,
    private val onItemClick: (Movie) ->Unit
) : RecyclerView.Adapter<MovieSearchPageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchPageViewHolder {
      return  MovieSearchPageViewHolder(
          ItemListCommonBinding.inflate(
              LayoutInflater.from(parent.context), parent, false
          ),
          onItemClick
      )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieSearchPageViewHolder, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}