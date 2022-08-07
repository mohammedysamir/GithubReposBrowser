package com.myasser.githubreposbrowser.screens

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.myasser.githubreposbrowser.Adapters.RepoRecyclerViewAdapter
import com.myasser.githubreposbrowser.R

class RepoListActivity : AppCompatActivity() {
    private lateinit var repoRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        findViewById<TextView>(R.id.nameTextView).text = intent.getStringExtra("name")
        findViewById<TextView>(R.id.repoNumbersTextView).text =
            getString(R.string.no_repositories, intent.getStringExtra("repo numbers"))
        repoRecyclerView = findViewById(R.id.repoRecyclerView)
        repoRecyclerView.adapter = RepoRecyclerViewAdapter(arrayListOf())
        //todo: fetch/access repo's list from search screen
    }
}