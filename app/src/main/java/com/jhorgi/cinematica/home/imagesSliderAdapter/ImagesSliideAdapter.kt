package com.jhorgi.cinematica.home.imagesSliderAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import coil.load
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.utils.formatDate
import com.jhorgi.cinematica.databinding.ImagesSliderItemBinding

class ImagesSliideAdapter(private val context: Context, private var imageList: List<Movie>): PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ImagesSliderItemBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )

        val movie = imageList[position]
        binding.ivImages.load("https://image.tmdb.org/t/p/w500/${movie.backdropPath}")
        binding.tittleTv.text = movie.title

        val releasedata= formatDate(movie.releaseDate)
        binding.releaseDateTv.text = "On $releasedata"




        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}