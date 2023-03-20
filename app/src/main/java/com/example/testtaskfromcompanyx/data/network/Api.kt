package com.example.testtaskfromcompanyx.data.network

import com.example.testtaskfromcompanyx.data.network.model.FlashSaleResponse
import com.example.testtaskfromcompanyx.data.network.model.LatestResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): LatestResponse?

    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale(): FlashSaleResponse?

    companion object {

        private const val BASE_URL = "https://run.mocky.io/"

        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}