package com.denwehrle.boilerplate.ui.welcome

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class WelcomeAdapter @Inject constructor(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private val fragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    /**
     * With this the ViewPager recreates itself if notifyDataSetChanged() is called. This is
     * needed to ensure every fragment reloads the corresponding data.
     */
    override fun getItemPosition(item: Any): Int {
        return POSITION_NONE
    }
}