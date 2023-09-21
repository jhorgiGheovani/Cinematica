package com.jhorgi.cinematica.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val list: List<FavoriteItem>,
    private val onItemClick: (FavoriteItem) -> Unit
    ): RecyclerView.Adapter<FavoriteListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}