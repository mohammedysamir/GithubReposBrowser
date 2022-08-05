package com.myasser.githubreposbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myasser.githubreposbrowser.ui.theme.GithubReposBrowserTheme

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubReposBrowserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {

}
/*
* UI:
*   1. Text field to enter repo name + language (optional) + button to search by repo name
*   2. Text field to enter user name to fetch all his repos + button -> will be displayed as list and may add pagination
* */