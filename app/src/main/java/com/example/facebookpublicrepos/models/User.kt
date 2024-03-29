package com.example.facebookpublicrepos.models

import com.google.gson.annotations.SerializedName

/**
 * The user model class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class User {

    //region Name
    @SerializedName("login")
    val name: String = ""
    //endregion

    //region Avatar URL
    @SerializedName("avatar_url")
    val avatar_url: String = ""
    //endregion
}