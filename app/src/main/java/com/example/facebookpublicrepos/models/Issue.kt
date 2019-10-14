package com.example.facebookpublicrepos.models

import com.google.gson.annotations.SerializedName

/**
 * The repository issue model class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class Issue {

    //region issue number
    @SerializedName("number")
    private val number: Int = 0
    //endregion

    //region title
    @SerializedName("title")
    private val title: String = ""
    //endregion

    //region description
    @SerializedName("body")
    private val description: String = ""
    //endregion

    //region user
    @SerializedName("user")
    private val user: User = User()
    //endregion
}