package com.jhorgi.cinematica.commonAdapter.movieSeeAllAdapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.jhorgi.cinematica.databinding.ItemLoadingBinding

class LoadingStateViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit):
    RecyclerView.ViewHolder(binding.root)  {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }
}