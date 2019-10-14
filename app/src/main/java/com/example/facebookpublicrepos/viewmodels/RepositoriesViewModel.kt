package com.example.facebookpublicrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.facebookpublicrepos.interfaces.RepositoryCallback
import com.example.facebookpublicrepos.models.Contributor
import com.example.facebookpublicrepos.models.Issue
import com.example.facebookpublicrepos.models.Repository
import com.example.facebookpublicrepos.repositories.GithubRepository

/**
 * The repository view model class which is part of the MVVM architecture. This view model communicate which the [GithubRepository] repository.
 * All response of the repository will be reported through the [RepositoryCallback] interface.
 *
 * @author Melchior Vrolijk
 * @since 13-10-2019
 */
@Suppress("UNCHECKED_CAST")
class RepositoriesViewModel : ViewModel()
{
    //region Local instances
    private var repositoryList: MutableLiveData<List<Repository>> = MutableLiveData()
    private var contributorList: MutableLiveData<List<Contributor>> = MutableLiveData()
    private var issuesList: MutableLiveData<List<Issue>> = MutableLiveData()
    private var repository: GithubRepository = GithubRepository.instance
    //endregion

    //region Retrieve list of public repository
    /**
     * Retrieve list of public repositories
     *
     * @param page_number The page number
     * @return [LiveData] List of the [Repository] objects
     */
    fun getRepositoryList(page_number: Int) : LiveData<List<Repository>> {

         repository.retrieveRepositoryList(page_number, object : RepositoryCallback {
             override fun onResponse(data: List<Any>) {
                 val response = data as List<Repository>
                 repositoryList.value = response
             }

             override fun onFailed(error: String) { }
         })

        return this.repositoryList
    }
    //endregion

    //region Retrieve list of repository contributors
    /**
     * Retrieve list of contributors
     *
     * @param repository_name The target repository name
     * @param page_number The page number
     * @return [LiveData] List of the [Repository] objects
     */
    fun getRepositoryContributors(repository_name: String, page_number: Int) : LiveData<List<Contributor>> {

        repository.retrieveRepositoryContributors(repository_name,page_number, object : RepositoryCallback {
            override fun onResponse(data: List<Any>) {

                val response = data as List<Contributor>
                contributorList.value = response
            }

            override fun onFailed(error: String) {}
        } )

        return this.contributorList
    }
    //endregion

    //region Retrieve list of repository issues
    /**
     * Retrieve list of issues
     *
     * @param repository_name The target repository name
     * @param page_number The page number
     * @return [LiveData] List of the [Repository] objects
     */
    fun getRepositoryIssues(repository_name: String, page_number: Int) : LiveData<List<Issue>> {

        repository.retrieveRepositoryIssues(repository_name,page_number, object : RepositoryCallback {
            override fun onResponse(data: List<Any>) {

                val response = data as List<Issue>
                issuesList.value = response
            }

            override fun onFailed(error: String) {}
        } )

        return this.issuesList
    }
    //endregion
}