package com.jhorgi.cinematica.searchPage.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jhorgi.cinematica.searchPage.searchResult.SearchFragmentContent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class SearchResultSectionsPagerAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = SearchFragmentContent()
        fragment.arguments = Bundle().apply {
            putInt(ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}

