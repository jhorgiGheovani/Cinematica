package com.jhorgi.cinematica.commonAdapter.listAdapterV1

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList1
import com.jhorgi.cinematica.databinding.ItemListCommonBinding

class ListViewHolderV1(
    private val binding: ItemListCommonBinding,
    private val onItemClick: (RecyclerViewDataList1) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: RecyclerViewDataList1) {

//        binding.PosterIV.setImageResource(R.drawable.emty_poster_images)

        if (data.posterPath != null) {
            data.let {
                binding.PosterIV.load("https://image.tmdb.org/t/p/w500/${data.posterPath}"){
//                    placeholder(R.drawable.emty_poster_images)

                    listener(
                        onStart = {
                            binding.progressBar.setAnimation("loadingSpinner.lottie")
                            binding.progressBar.playAnimation()
                        },
                        onSuccess = {_, _ ->
                            binding.progressBar.visibility = View.GONE
                        },
                        onError = {_, _ ->
                            binding.progressBar.visibility = View.GONE
                        }
                    )
                }
            }
        }else{
            binding.PosterIV.setImageResource(R.drawable.emty_poster_images)
        }


        binding.tittleTV.text = data.tittle
        binding.genres.text = data.genres?.joinToString (", ")
        binding.textStart.text = data.rating

        itemView.setOnClickListener { onItemClick(data) }
    }
}