package com.example.facebookpublicrepos.models

import com.google.gson.annotations.SerializedName

/**
 * The repository issue model class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class Issue {

    //region Name
    @SerializedName("id")
    val id: Int = 0
    //endregion

    //region issue number
    @SerializedName("number")
    val number: Int = 0
    //endregion

    //region title
    @SerializedName("title")
    val title: String = ""
    //endregion

    //region description
    @SerializedName("body")
    val description: String = ""
    //endregion

    //region user
    @SerializedName("user")
    val user: User = User()
    //endregion

    //region Equals function
    /**
     * @inheritdoc
     */
    override fun equals(other: Any?): Boolean
    {
        if (!(other is Issue)) {
            return false
        }

        val repo = other as Issue
        return this.id == repo.id
    }
    //endregion

    //region Hashcode function
    override fun hashCode(): Int {
        return 31*this.hashCode()
    }
    //endregion
}