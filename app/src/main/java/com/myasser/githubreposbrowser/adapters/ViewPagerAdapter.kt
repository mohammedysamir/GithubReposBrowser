package com.myasser.githubreposbrowser.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: ArrayList<Fragment>,
) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount() = fragments.size

    override fun getItem(position: Int) = fragments[position]

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}