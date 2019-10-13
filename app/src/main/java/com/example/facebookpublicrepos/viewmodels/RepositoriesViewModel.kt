package com.example.facebookpublicrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.facebookpublicrepos.interfaces.RepositoryCallback
import com.example.facebookpublicrepos.models.Repository
import com.example.facebookpublicrepos.repositories.RepositoriesListRepository

@Suppress("UNCHECKED_CAST")
class RepositoriesViewModel : ViewModel(), RepositoryCallback
{
    private var repositoryList: MutableLiveData<List<Repository>> = MutableLiveData()
    private var repository: RepositoriesListRepository = RepositoriesListRepository.instance

    fun getRepositoryList(page_number: Int) : LiveData<List<Repository>> {

         repository.retrieveRepositoryList(page_number, this)

        return this.repositoryList
    }

    override fun onResponse(data: List<Any>) {

        val response = data as List<Repository>
        this.repositoryList.value = response
    }

    override fun onFailed(error: String) {}
}