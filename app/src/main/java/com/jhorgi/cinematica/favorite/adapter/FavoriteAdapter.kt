package com.jhorgi.cinematica.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.FavoriteMovie
import com.jhorgi.cinematica.databinding.ItemFavoriteMovieBinding

class FavoriteAdapter(private val list: List<FavoriteMovie>): RecyclerView.Adapter<FavoriteListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder(
            ItemFavoriteMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}