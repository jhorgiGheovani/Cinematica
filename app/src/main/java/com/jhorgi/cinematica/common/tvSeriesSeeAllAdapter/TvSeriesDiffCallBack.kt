package com.jhorgi.cinematica.common.tvSeriesSeeAllAdapter

import androidx.recyclerview.widget.DiffUtil
import com.jhorgi.cinematica.core.domain.model.TvSeries

class TvSeriesDiffCallBack : DiffUtil.ItemCallback<TvSeries>() {
    override fun areItemsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
        return oldItem == newItem
    }
}