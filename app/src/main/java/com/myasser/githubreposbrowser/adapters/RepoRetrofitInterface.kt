package com.myasser.githubreposbrowser.adapters

import com.myasser.githubreposbrowser.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoRetrofitInterface {
    //todo: test functions
    //can add some queries too -> page number, per_page = # results/page
    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") ownerName: String): Call<ArrayList<Repository>>

    //to list all public repos in github
    @GET("/repositories")
    fun getPublicRepositories(): Call<ArrayList<Repository>>

    //from search api
    //query: q = {name}+language:{language}[&sort = [stars|forks|help-wanted-issues|updated]&order=[desc|asc]] -> [] "optional"
    @GET("/search/repositories")
    fun getRepoByNameAndLanguage(@Query("q") query: String): Call<ArrayList<Repository>>
}