package com.jhorgi.cinematica.seeAllPages

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.databinding.ActivitySeeAllBinding
import com.jhorgi.cinematica.searchPage.SearchFragment

class SeeAllActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeAllBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all)

        binding = ActivitySeeAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataTittle = intent.getStringExtra(SearchFragment.TYPE_TITTLE_DATA)

        window?.statusBarColor = ContextCompat.getColor(this, R.color.main_background)
        //support action setting
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar_tittle)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.main_background)))
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val customActionBar = supportActionBar?.customView
        val textTittle = customActionBar?.findViewById<TextView>(R.id.textTittle)
        textTittle?.text = dataTittle


        val tabs = binding.tabs
        val viewPager = binding.viewPager


        val sectionPagerAdapter = SectionsPagerSeeAllPagesAdapter(this)
        viewPager.adapter = sectionPagerAdapter


        TabLayoutMediator(tabs,viewPager) {tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }


    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}