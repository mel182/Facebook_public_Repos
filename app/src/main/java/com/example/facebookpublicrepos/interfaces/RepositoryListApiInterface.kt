package com.example.facebookpublicrepos.interfaces

import com.example.facebookpublicrepos.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryListApiInterface {

    @GET("/orgs/{organization_name}/repos")
    fun getRepositories(@Path("organization_name", encoded = true) organization: String, @Query("page") page_number:Int, @Query("per_page") amount_per_page:Int): Call<List<Repository>>
}