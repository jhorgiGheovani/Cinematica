package com.jhorgi.cinematica.searchPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class SearchResultAdapter(
    private val list: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<SearchResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}