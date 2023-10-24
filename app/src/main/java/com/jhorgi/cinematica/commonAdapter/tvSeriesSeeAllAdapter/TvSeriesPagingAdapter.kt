package com.jhorgi.cinematica.commonAdapter.tvSeriesSeeAllAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.databinding.ItemFavoriteBinding

class TvSeriesPagingAdapter: PagingDataAdapter<TvSeries, TvSeriesListPagingViewHolder>(TvSeriesDiffCallBack()) {

    var onItemClick: ((TvSeries)-> Unit)? = null
    override fun onBindViewHolder(holder: TvSeriesListPagingViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvSeriesListPagingViewHolder {
       val holder = TvSeriesListPagingViewHolder(
           ItemFavoriteBinding.inflate(
               LayoutInflater.from(parent.context), parent, false
           )
       )

        holder.binding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { tvSeries ->
                    onItemClick?.invoke(tvSeries)
                }
            }

        }
        return holder
    }

}