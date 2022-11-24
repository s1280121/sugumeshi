package com.example.every_restautant.`interface`

import com.example.every_restautant.data.RestaurantData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("gourmet/v1/?key=b19fe8c12358e3b5&format=json")
    suspend fun getReataurantData(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("keyword") keyword: String,
        @Query("range") range: String,
    ): Response<RestaurantData>
}