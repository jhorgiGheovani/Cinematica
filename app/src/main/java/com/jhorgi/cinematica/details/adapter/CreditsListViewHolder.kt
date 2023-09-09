package com.jhorgi.cinematica.details.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.databinding.ItemCreditsBinding

class CreditsListViewHolder(
    private val binding: ItemCreditsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Credit) {

        if (data.imagesPath != null) {
            data.let {
                binding.avatar.load("https://image.tmdb.org/t/p/w500/${data.imagesPath}")
            }
        } else {
            binding.avatar.setImageResource(R.drawable.empty_photo_ic)
        }


        binding.name.text = data.name
        binding.position.text = data.positionOrCharacter
    }

}