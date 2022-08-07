package com.myasser.githubreposbrowser.screens

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.myasser.githubreposbrowser.R
import com.myasser.githubreposbrowser.adapters.RepoRecyclerViewAdapter
import com.myasser.githubreposbrowser.fragments.RepoNameFragment
import com.myasser.githubreposbrowser.fragments.UserNameFragment
import com.myasser.githubreposbrowser.models.Repository

class RepoListActivity : AppCompatActivity() {
    private lateinit var repoRecyclerView: RecyclerView
    private var isFetchedByUser: Boolean = true
    private lateinit var repos: ArrayList<Repository>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        findViewById<TextView>(R.id.nameTextView).text = intent.getStringExtra("name")
        findViewById<TextView>(R.id.repoNumbersTextView).text =
            getString(R.string.no_repositories, intent.getStringExtra("repo numbers"))
        repoRecyclerView = findViewById(R.id.repoRecyclerView)
        repos = arrayListOf()
        isFetchedByUser = intent.getBooleanExtra("by user", true)
        //fetch repositories list
        repos = if (isFetchedByUser) {
            UserNameFragment.userRepositories
        } else {
            RepoNameFragment.repositories
        }
        repoRecyclerView.adapter = RepoRecyclerViewAdapter(repos)
    }
}
//todo: add search functionality