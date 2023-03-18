package com.example.every_restaurant.viewModel

import com.example.every_restaurant.`interface`.APIInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit2の設定

class ReataurantDataRepository {
    companion object Factory {
        val instance: ReataurantDataRepository

        get() { return ReataurantDataRepository() }
    }

    private val client = OkHttpClient.Builder().build()

    suspend fun getReataurantData(lat: String, lng: String, keyword: String, range: String)
    = Retrofit.Builder()
        .baseUrl("http://webservice.recruit.co.jp/hotpepper/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(APIInterface::class.java)
        .getReataurantData(lat, lng, keyword, range)
}