package com.cosmicrockets.ui.rocket

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cosmicrockets.ui.rocket.RocketFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val list: List<RocketFragment>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}