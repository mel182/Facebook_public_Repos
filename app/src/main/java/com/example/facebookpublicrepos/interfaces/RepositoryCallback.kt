package com.example.facebookpublicrepos.interfaces

/**
 * This is the repository callback interface which is will be used to communicate with the repositories.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
interface RepositoryCallback {

    //region onResponse function
    /**
     * The on response function which provide a list of [Any] which than can be cast into the desire data type
     * @param data The list of [Any]
     */
    fun onResponse(data: List<Any>)
    //endregion

    //region onFailed function
    /**
     * The on failed function which report any error that occur while trying to communicate with the repository.
     *
     * @param error Error that occur while trying to communicate with the repository
     */
    fun onFailed(error: String)
    //endregion
}