package com.myasser.githubreposbrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.myasser.githubreposbrowser.R

class RepoNameFragment : Fragment(), View.OnClickListener {
    private lateinit var repoNameEditText: EditText
    private lateinit var repoLanguageEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_name, container, false)
        view.findViewById<AppCompatButton>(R.id.searchByRepoName).setOnClickListener(this)
        repoNameEditText = view.findViewById(R.id.repoNameEditText)
        repoLanguageEditText = view.findViewById(R.id.repoLanguageEditText)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.searchByRepoName -> {
                //todo: implement fetching data from API using repo name, language and then navigate to show list of repos
                Toast.makeText(v.context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}