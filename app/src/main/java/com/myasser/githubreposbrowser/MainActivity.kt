package com.myasser.githubreposbrowser

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add listeners
        findViewById<AppCompatButton>(R.id.save_name_button).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.search_repo_button).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.search_github_user_button).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var intent: Intent? = null
        when (v?.id) {
            R.id.save_name_button -> {
                val userNameEdit = findViewById<TextInputEditText>(R.id.user_name_edit_text)
                if (!handleValidation(
                        userNameEdit,
                        findViewById(R.id.name_layout),
                        "Can't save empty name"
                    )
                ) {
                    val userName = userNameEdit.text.toString()
                    //todo: complete user save
                }
            }
            R.id.search_repo_button -> {
                val repoNameEditText =
                    findViewById<TextInputEditText>(R.id.repo_name_edit_text)
                val repoLanguageEditText =
                    findViewById<TextInputEditText>(R.id.language_edit_text)
                //if the repo field is not empty
                if (!handleValidation(
                        repoNameEditText,
                        findViewById(R.id.repoLayout),
                        "Can't process with empty repository name"
                    )
                ) {
                    val repoName =
                        repoNameEditText.text.toString()
                    val repoLanguage =
                        repoLanguageEditText.text.toString()
                    //identify search type, repo name and language
                    intent?.putExtra(Constants.key_repo_search, repoName)
                    intent?.putExtra(Constants.key_query, Constants.search_by_repo)
                    intent?.putExtra(Constants.key_language, repoLanguage)
                }

            }
            R.id.search_github_user_button -> {
                val userNameEditText = findViewById<TextInputEditText>(R.id.user_name_edit_text)
                if (!handleValidation(
                        userNameEditText,
                        findViewById(R.id.githubLayout),
                        "Can't proceed with empty github user name"
                    )
                ) {
                    val userName =
                        userNameEditText.text.toString()
                    //identify search type, user name
                    intent?.putExtra(Constants.key_github_username, userName)
                    intent?.putExtra(Constants.key_query, Constants.search_by_user)
                }
            }
        }
        startActivity(intent)
    }

    private fun handleValidation(
        editText: TextInputEditText,
        textInputLayout: TextInputLayout,
        errorMessage: String
    ): Boolean {
        return if (editText.text?.toString()?.isEmpty() == true) {
            textInputLayout.error = errorMessage
            false
        } else {
            textInputLayout.isErrorEnabled = false
            true
        }
    }
}