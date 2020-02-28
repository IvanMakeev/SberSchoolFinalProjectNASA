package com.example.presentation.ui

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.presentation.R
import com.example.presentation.ui.fragment.ImageFragment
import com.example.presentation.ui.fragment.MainFragment
import com.example.presentation.utils.DateUtils.getDateOffset
import com.example.presentation.utils.DateUtils.lengthOfYear

class MainActivity : AppCompatActivity(), ZoomClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pager = findViewById<ViewPager>(R.id.pager)
        val pagerAdapter: PagerAdapter = PageAdapter(supportFragmentManager, 0, this)
        pager.adapter = pagerAdapter
    }

    override fun onZoomImage(url: String) {
        val imageFragment = ImageFragment.newInstance(url)
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.root_view, imageFragment)
                .commit()
    }

    private class PageAdapter internal constructor(fm: FragmentManager, behavior: Int, context: Context) : FragmentStatePagerAdapter(fm, behavior) {
        private val mResources: Resources = context.resources

        override fun getItem(position: Int) = MainFragment.newInstance(position)

        override fun getCount() = lengthOfYear()


        override fun getPageTitle(position: Int) = mResources.getString(R.string.astronomy_picture, getDateOffset(position))


    }
}