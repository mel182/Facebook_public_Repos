package com.example.facebookpublicrepos.models

import com.google.gson.annotations.SerializedName

/**
 * The repository model class
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class Repository {

    //region ID
    @SerializedName("id")
    val id: Int = 0
    //endregion

    //region Name
    @SerializedName("name")
    val name: String = ""
    //endregion

    //region Description
    @SerializedName("description")
    val description: String = ""
    //endregion

    //region Equals function
    /**
     * @inheritdoc
     */
    override fun equals(other: Any?): Boolean
    {
        if (!(other is Repository)) {
            return false
        }

        val repo = other as Repository
        return this.id == repo.id
    }
    //endregion

    //region Hashcode function
    override fun hashCode(): Int {
        return 31*this.hashCode()
    }
    //endregion
}