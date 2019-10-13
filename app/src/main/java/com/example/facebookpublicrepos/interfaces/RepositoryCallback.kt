package com.example.facebookpublicrepos.interfaces

interface RepositoryCallback {

    fun onResponse(data: List<Any>)
    fun onFailed(error: String)
}