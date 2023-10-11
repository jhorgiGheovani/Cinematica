package com.jhorgi.cinematica.seeAllPages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerSeeAllPagesAdapter(activity: AppCompatActivity, private val type: String) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = SeeAllContentFragment()
        fragment.arguments = Bundle().apply {
            putInt(SeeAllContentFragment.ARG_SECTION_NUMBER, position+1)
            putString(SeeAllContentFragment.ARG_TYPE, type)
        }
        return fragment
    }
}