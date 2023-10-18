package com.jhorgi.cinematica.commonAdapter.listAdapterV1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList1
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class ListAdapterV1(
    private val list: List<RecyclerViewDataList1>,
    private val onItemClick: (RecyclerViewDataList1) ->Unit
): RecyclerView.Adapter<ListViewHolderV1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolderV1 {
        return ListViewHolderV1(
            ItemListCommonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ListViewHolderV1, position: Int) {
        val data = list[position]

        holder.bind(data)
    }

}