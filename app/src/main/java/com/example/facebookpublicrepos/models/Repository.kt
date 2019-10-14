package com.example.facebookpublicrepos.models

import com.google.gson.annotations.SerializedName

/**
 * The repository model class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class Repository {

    //region Name
    @SerializedName("name")
    private val name: String = ""
    //endregion

    //region Description
    @SerializedName("description")
    private val description: String = ""
    //endregion
}