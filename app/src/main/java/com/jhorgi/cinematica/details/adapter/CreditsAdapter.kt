package com.jhorgi.cinematica.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.databinding.ItemCreditsBinding

class CreditsAdapter(private val list : List<Credit>)  : RecyclerView.Adapter<CreditsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditsListViewHolder {
        return CreditsListViewHolder(
            ItemCreditsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CreditsListViewHolder, position: Int) {
        val data = list[position]

        holder.bind(data)

    }
}