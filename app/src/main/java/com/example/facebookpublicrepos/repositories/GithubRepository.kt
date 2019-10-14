package com.example.facebookpublicrepos.repositories

import com.example.facebookpublicrepos.api.ApiClient
import com.example.facebookpublicrepos.constants.Constant
import com.example.facebookpublicrepos.enumeration.Organization
import com.example.facebookpublicrepos.interfaces.api.RepositoryApiInterface
import com.example.facebookpublicrepos.interfaces.RepositoryCallback
import com.example.facebookpublicrepos.models.Contributor
import com.example.facebookpublicrepos.models.Issue
import com.example.facebookpublicrepos.models.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This the singleton class that contain all the github repository API related properties.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class GithubRepository
{
    //region Local instances
    private var repositoryApiInterface : RepositoryApiInterface = ApiClient.getApiClient().create(RepositoryApiInterface::class.java)
    //endregion

    //region Retrieve repository list
    /**
     * Retrieve repository list through http request to the github API.
     * Since this is a async task the respond will be send through the [RepositoryCallback] interface.
     *
     * @param page_number The pagination page number
     * @param callback The [RepositoryCallback] instance
     */
    fun retrieveRepositoryList(page_number: Int,callback: RepositoryCallback) {

        val call : Call<List<Repository>> = repositoryApiInterface
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
    //endregion

    //region Retrieve repository contributors
    /**
     * Retrieve repository contributors through the github API.
     * Since this is a async task the respond will be send through the [RepositoryCallback] interface.
     *
     * @param repository_name The repository name
     * @param page_number The pagination page number
     * @param callback The [RepositoryCallback] instance
     */
    fun retrieveRepositoryContributors(repository_name: String, page_number: Int,callback: RepositoryCallback) {

        val call : Call<List<Contributor>> = repositoryApiInterface
            .getContributors(
                Organization.FACEBOOK.value,
                repository_name,
                page_number,
                Constant.AMOUNT_PER_PAGE
            )

        call.enqueue(object : Callback<List<Contributor>>{

            override fun onResponse(call: Call<List<Contributor>>, response: Response<List<Contributor>>) {
                callback.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                callback.onFailed(if (t.message != null) t.message!! else "")
            }
        })
    }
    //endregion

    //region Retrieve repository issues
    /**
     * Retrieve repository issues through the github API.
     * Since this is a async task the respond will be send through the [RepositoryCallback] interface.
     *
     * @param repository_name The repository name
     * @param page_number The pagination page number
     * @param callback The [RepositoryCallback] instance
     */
    fun retrieveRepositoryIssues(repository_name: String, page_number: Int,callback: RepositoryCallback) {

        val call : Call<List<Issue>> = repositoryApiInterface
            .getIssues(
                Organization.FACEBOOK.value,
                repository_name,
                page_number,
                Constant.AMOUNT_PER_PAGE
            )

        call.enqueue(object : Callback<List<Issue>>{

            override fun onResponse(call: Call<List<Issue>>, response: Response<List<Issue>>) {
                callback.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
                callback.onFailed(if (t.message != null) t.message!! else "")
            }
        })
    }
    //endregion

    //region Companion object
    /**
     * Companion object that contains the singleton class global instance
     */
    companion object {
        private var INSTANCE: GithubRepository? = null

        val instance: GithubRepository
        get() {
            if (INSTANCE == null)
                INSTANCE = GithubRepository()

            return INSTANCE!!
        }
    }
    //endregion
}
