package com.myasser.githubreposbrowser.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.myasser.githubreposbrowser.Adapters.ViewPagerAdapter
import com.myasser.githubreposbrowser.R
import com.myasser.githubreposbrowser.fragments.RepoNameFragment
import com.myasser.githubreposbrowser.fragments.UserNameFragment

class SearchScreen : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)
        //define list of fragments
        val fragmentList = arrayListOf(
            RepoNameFragment(), UserNameFragment()
        )
        //define viewPager
        viewPager = findViewById(R.id.searchViewPager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        //define tabLayout
        tabLayout = findViewById(R.id.tabLayoutView)
        defineTabLayout()
        defineListener()

    }

    private fun defineTabLayout() {
        //add tabs
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab1_title)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab2_title)))
        //define gravity
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        //hook with adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    //todo: test if listener will be hooked
    private fun defineListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}