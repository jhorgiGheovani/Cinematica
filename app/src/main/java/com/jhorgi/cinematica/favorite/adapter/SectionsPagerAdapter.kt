package com.jhorgi.cinematica.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jhorgi.cinematica.favorite.movie.FavoriteItemFragment
import com.jhorgi.cinematica.favorite.tvSeries.FavoriteTvSeriesFragment

class SectionsPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteItemFragment()
            1 -> fragment = FavoriteTvSeriesFragment()
        }
        return fragment as Fragment
    }
}