package com.studio.yami.ajpfinal.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.ui.fragment.favorite.FavMovieFragment
import com.studio.yami.ajpfinal.ui.fragment.favorite.FavTvFragment

private val TAB_TITLES = arrayOf(
    R.string.movie,
    R.string.tv
)

private val fragment = arrayOf(
    FavMovieFragment(),
    FavTvFragment()
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}