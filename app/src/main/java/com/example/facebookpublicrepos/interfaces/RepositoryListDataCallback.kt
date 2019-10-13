package com.example.facebookpublicrepos.interfaces

interface RepositoryListDataCallback {

    fun onResponse(data: List<Any>)
    fun onFailed(error: String)
}