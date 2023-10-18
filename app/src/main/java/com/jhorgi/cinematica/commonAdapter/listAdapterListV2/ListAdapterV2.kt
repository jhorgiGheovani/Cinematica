package com.jhorgi.cinematica.commonAdapter.listAdapterListV2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList2
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class ListAdapterV2(
    private val list: List<RecyclerViewDataList2>,
    private val onItemClick: (RecyclerViewDataList2)-> Unit
): RecyclerView.Adapter<ListViewHolderV2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolderV2 {
        return  ListViewHolderV2(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolderV2, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}