package com.example.facebookpublicrepos.models

import com.google.gson.annotations.SerializedName

/**
 * The repository contributor model class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class Contributor {

    //region Name
    @SerializedName("login")
    val name: String = ""
    //endregion

    //region Name
    @SerializedName("id")
    val id: Int = 0
    //endregion

    //region Avatar URL
    @SerializedName("avatar_url")
    val avatar_url: String = ""
    //endregion

    //region Contributions
    @SerializedName("contributions")
    val contributions: Int = 0
    //endregion

    //region Equals function
    /**
     * @inheritdoc
     */
    override fun equals(other: Any?): Boolean
    {
        if (!(other is Contributor)) {
            return false
        }

        val repo = other as Contributor
        return this.id == repo.id
    }
    //endregion

    //region Hashcode function
    override fun hashCode(): Int {
        return 31*this.hashCode()
    }
    //endregion
}