package com.example.facebookpublicrepos.repositories

import com.example.facebookpublicrepos.api.ApiClient
import com.example.facebookpublicrepos.constants.Constant
import com.example.facebookpublicrepos.enumeration.Organization
import com.example.facebookpublicrepos.interfaces.RepositoryListApiInterface
import com.example.facebookpublicrepos.interfaces.RepositoryCallback
import com.example.facebookpublicrepos.models.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Singleton pattern
class RepositoriesListRepository
{
    private var repositoryApiInterface : RepositoryListApiInterface? = null

    fun retrieveRepositoryList(page_number: Int,callback: RepositoryCallback) {

        repositoryApiInterface = ApiClient.getApiClient().create(RepositoryListApiInterface::class.java)

        val call : Call<List<Repository>> = repositoryApiInterface!!
            .getRepositories(
                Organization.FACEBOOK.value,
                page_number,
                Constant.AMOUNT_PER_PAGE)

        call.enqueue(object : Callback<List<Repository>>{

            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                callback.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                callback.onFailed(if (t.message != null) t.message!! else "")
            }
        })
    }

    companion object {
        private var INSTANCE: RepositoriesListRepository? = null

        val instance: RepositoriesListRepository
        get() {
            if (INSTANCE == null)
                INSTANCE = RepositoriesListRepository()

            return INSTANCE!!
        }
    }
}
