package com.example.facebookpublicrepos.api

import com.example.facebookpublicrepos.constants.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is API client class which http client request to external data source.
 *
 * @author Melchior Vrolijk
 * @since 1.0
 */
class ApiClient {

    companion object {

        //region Local instances
        var retrofit : Retrofit? = null
        //endregion

        //region Get api client instance
        /**
         * Get the api client instance
         * @return The api client which is a Retrofit instance
         */
        fun getApiClient() : Retrofit {

            if (retrofit == null) {
                retrofit = Retrofit
                    .Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }
        //endregion
    }
}