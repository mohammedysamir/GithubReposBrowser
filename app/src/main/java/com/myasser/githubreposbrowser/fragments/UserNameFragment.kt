package com.myasser.githubreposbrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.myasser.githubreposbrowser.R

class UserNameFragment : Fragment(), View.OnClickListener {
    private lateinit var userNameEditText: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_name, container, false)
        userNameEditText = view.findViewById(R.id.userNameEditText)
        view.findViewById<AppCompatButton>(R.id.searchByUserName).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.searchByUserName -> {
                //todo: implement retrofit fetching repos by user name and then navigate to show list of repos
            }
        }
    }
}