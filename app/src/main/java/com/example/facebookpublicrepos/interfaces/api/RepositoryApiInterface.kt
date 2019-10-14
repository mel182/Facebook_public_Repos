package com.example.facebookpublicrepos.interfaces.api

import com.example.facebookpublicrepos.models.Contributor
import com.example.facebookpublicrepos.models.Issue
import com.example.facebookpublicrepos.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This is the repository api interface which will be used by [retrofit2] to communicate with the external source.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
interface RepositoryApiInterface {

    //region Get repositories list
    @GET("/orgs/{organization_name}/repos")
    fun getRepositories(@Path("organization_name", encoded = true) organization: String, @Query("page") page_number:Int, @Query("per_page") amount_per_page:Int): Call<List<Repository>>
    //endregion

    //region Get repository contributors
    @GET("/repos/{organization_name}/{repository_name}/contributors")
    fun getContributors(@Path("organization_name", encoded = true) organization: String, @Path("repository_name", encoded = true) repository_name: String, @Query("page") page_number:Int, @Query("per_page") amount_per_page:Int): Call<List<Contributor>>
    //endregion

    //region Get repository issue
    @GET("/repos/{organization_name}/{repository_name}/issues")
    fun getIssues(@Path("organization_name", encoded = true) organization: String, @Path("repository_name", encoded = true) repository_name: String, @Query("page") page_number:Int, @Query("per_page") amount_per_page:Int): Call<List<Issue>>
    //endregion
}