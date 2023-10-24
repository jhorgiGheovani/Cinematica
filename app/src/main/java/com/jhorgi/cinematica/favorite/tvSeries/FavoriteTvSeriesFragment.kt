package com.jhorgi.cinematica.favorite.tvSeries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.commonAdapter.listAdapterListV2.ListAdapterV2
import com.jhorgi.cinematica.core.utils.DataMapper
import com.jhorgi.cinematica.databinding.FragmentFavoriteTvSeriesBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteTvSeriesFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteTvSeriesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFavoriteTvSeries.layoutManager = layoutManager

        favoriteViewModel.getListOfFavoriteItem(DetailsActivity.TV_SERIES_TYPE)

        favoriteViewModel.favoriteItem.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.animationView.setAnimation("emptyFavoriteData.lottie")
                binding.animationView.playAnimation()
            }

            val data = DataMapper.mapFavoriteItemToRecyclerViewDataList2(it)
            val adapter = ListAdapterV2(data) { clickMovie ->
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.id)
                intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                startActivity(intent)
            }
            binding.rvFavoriteTvSeries.adapter = adapter
        }

    }
}