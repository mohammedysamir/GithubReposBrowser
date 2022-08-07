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
import com.myasser.githubreposbrowser.screens.RepoListActivity
import com.myasser.githubreposbrowser.screens.SearchScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserNameFragment : Fragment() {
    private lateinit var userNameEditText: EditText

    companion object {
        lateinit var userRepositories: ArrayList<Repository>
    }

    init {
        userRepositories = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_name, container, false)
        userNameEditText = view.findViewById(R.id.userNameEditText)
        view.findViewById<AppCompatButton>(R.id.searchByUserName).setOnClickListener {
            val userRepoCall =
                SearchScreen.githubAPI.getUserRepos(ownerName = userNameEditText.text.toString())
            userRepoCall.enqueue(object : Callback<ArrayList<Repository>> {
                override fun onResponse(
                    call: Call<ArrayList<Repository>>,
                    response: Response<ArrayList<Repository>>,
                ) {
                    //todo: test fetching, error messages
                    if (response.body()?.size!! > 0) {
                        userRepositories = response.body()!!
                        //navigate to RepoListActivity -> access list from there
                        startActivity(Intent(context, RepoListActivity::class.java).apply {
                            putExtra("by user", true)
                            putExtra("name", userNameEditText.text.toString())
                            putExtra("repo numbers", RepoNameFragment.repositories.size)
                        })
                    } else {
                        //notify user that user's name is not found
                        Toast.makeText(context,
                            getString(R.string.user_not_found_error),
                            Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Repository>>, t: Throwable) {
                    Toast.makeText(context,
                        getString(R.string.fetch_failure),
                        Toast.LENGTH_LONG).show()
                }

            })
        }
        return view
    }
}