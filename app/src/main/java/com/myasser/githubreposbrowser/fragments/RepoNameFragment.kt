package com.myasser.githubreposbrowser.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.myasser.githubreposbrowser.R
import com.myasser.githubreposbrowser.models.Repository
import com.myasser.githubreposbrowser.models.Result
import com.myasser.githubreposbrowser.screens.RepoListActivity
import com.myasser.githubreposbrowser.screens.SearchScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoNameFragment : Fragment() {
    private lateinit var repoNameEditText: EditText
    private lateinit var repoLanguageEditText: EditText

    companion object {
        lateinit var repositories: ArrayList<Repository>
    }

    init {
        repositories = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_name, container, false)
        repoNameEditText = view.findViewById(R.id.repoNameEditText)
        repoLanguageEditText = view.findViewById(R.id.repoLanguageEditText)

        view.findViewById<AppCompatButton>(R.id.searchByRepoName).setOnClickListener {
            //validate input text
            if (repoNameEditText.text.isNotEmpty()) {
                //todo: add coroutine here and display loading
                val repoCall =
                    SearchScreen.githubAPI.getRepoByNameAndLanguage(query = "${repoNameEditText.text.trim()}+language:${repoLanguageEditText.text.trim()}&sort=stars&order=desc")
                repoCall.enqueue(object : Callback<Result> {
                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        if (response.isSuccessful) {
                            repositories = (response.body()?.items as ArrayList<Repository>?)!!
                            if (repositories.size > 0) {
                                //navigate to RepoListActivity -> access list from there
                                startActivity(Intent(context, RepoListActivity::class.java).apply {
                                    putExtra("by user", false)
                                    putExtra("name", repoNameEditText.text.toString())
                                    putExtra("repo numbers", repositories.size.toString())
                                })
                            } else {
                                //notify user that user's name is not found
                                Toast.makeText(context,
                                    getString(R.string.user_not_found_error),
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Result>, t: Throwable) {
                        Toast.makeText(context,
                            getString(R.string.fetch_failure),
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                })
                //                repoCall.enqueue(object : Callback<ArrayList<Repository>> {
//                    override fun onResponse(
//                        call: Call<ArrayList<Repository>>,
//                        response: Response<ArrayList<Repository>>,
//                    ) {
//                        if (response.isSuccessful) {
//                            repositories = response.body()!!
//                            if (repositories.size > 0) {
//                                //navigate to RepoListActivity -> access list from there
//                                startActivity(Intent(context, RepoListActivity::class.java).apply {
//                                    putExtra("by user", false)
//                                    putExtra("name", repoNameEditText.text.toString())
//                                    putExtra("repo numbers", repositories.size)
//                                })
//                            } else {
//                                //notify user that user's name is not found
//                                Toast.makeText(context,
//                                    getString(R.string.user_not_found_error),
//                                    Toast.LENGTH_LONG).show()
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ArrayList<Repository>>, t: Throwable) {
//                        Toast.makeText(context,
//                            getString(R.string.fetch_failure),
//                            Toast.LENGTH_SHORT)
//                            .show()
//                    }

//            })
            } else {
                repoNameEditText.error = "Can't search with empty repository name!"
            }
        }
        return view
    }
}