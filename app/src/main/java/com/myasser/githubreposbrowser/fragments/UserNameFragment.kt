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

class UserNameFragment : Fragment(){
    private lateinit var userNameEditText: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_name, container, false)
        userNameEditText = view.findViewById(R.id.userNameEditText)
        view.findViewById<AppCompatButton>(R.id.searchByUserName).setOnClickListener{
            //todo: implement retrofit fetching repos by user name and then navigate to show list of repos
            Toast.makeText(context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}