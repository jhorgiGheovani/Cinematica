package com.jhorgi.cinematica.commonAdapter.tvSeriesListAdapterV1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.databinding.ItemListStyle1Binding

class TvSeriesListAdapterV1(
    private val list: List<TvSeries>,
    private val onItemClick: (TvSeries)->Unit
) : RecyclerView.Adapter<TvSeriesListViewHolderV1>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesListViewHolderV1 {
        return TvSeriesListViewHolderV1(
            ItemListStyle1Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TvSeriesListViewHolderV1, position: Int) {
        val data = list[position]

        holder.bind(data)
    }

}