package com.jhorgi.cinematica.common.tvListAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class TvListAdapter(
    private val list: List<TvSeries>,
    private val onItemClick: (TvSeries) ->Unit
) : RecyclerView.Adapter<TvListSearchPageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListSearchPageViewHolder {
        return  TvListSearchPageViewHolder(
            ItemListCommonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TvListSearchPageViewHolder, position: Int) {
        val data = list[position]

        holder.bind(data)
    }
}