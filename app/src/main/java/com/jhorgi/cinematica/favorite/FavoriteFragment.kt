package com.jhorgi.cinematica.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.databinding.FragmentFavoriteBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.favorite.adapter.FavoriteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFavorite.layoutManager = layoutManager

        favoriteViewModel.getFavorite()

        favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner){
            val adapter = FavoriteAdapter(it){clickMovie->
                val intent = Intent(activity,DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.id)
                startActivity(intent)
            }
            binding.rvFavorite.adapter = adapter

        }



    }

}