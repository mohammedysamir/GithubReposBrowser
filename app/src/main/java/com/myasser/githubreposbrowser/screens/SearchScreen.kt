package com.myasser.githubreposbrowser.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.myasser.githubreposbrowser.R
import com.myasser.githubreposbrowser.adapters.RepoRetrofitInterface
import com.myasser.githubreposbrowser.adapters.ViewPagerAdapter
import com.myasser.githubreposbrowser.fragments.RepoNameFragment
import com.myasser.githubreposbrowser.fragments.UserNameFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchScreen : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    companion object {
        private lateinit var retrofit: Retrofit
        lateinit var githubAPI: RepoRetrofitInterface

        //make it suspend function so we can stop it
        suspend fun buttonLoader(button: AppCompatButton, mainText: String, recurringText: String) {
            //save old text (if we need it)
            val oldText = button.text.toString()
            //use recurring text and loop through
            var i = 0
            while (true) {
                //assign new text + recurring substring of recurringText
                button.text = "${mainText} ${recurringText.substring(0, i)}"
                i = (i + 1) % recurringText.length //circular access
            }
        }
    }

    init {
        //retrofit
        retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        githubAPI = retrofit.create(RepoRetrofitInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)
        //define list of fragments
        val fragmentList = arrayListOf(
            RepoNameFragment(), UserNameFragment()
        )
        tabLayout = findViewById(R.id.tabLayoutView)
        viewPager = findViewById(R.id.searchViewPager)

        //define tabLayout
        defineListener()

        //define viewPager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


    }

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
//todo: create function to display loading text on buttons while fetching data -> use coroutine to handle those changes
//function must take 2 main params (main text, recurring text) -> recurring text will be displayed one-by-one like "..." -> . -> . . -> . . . using circular array access