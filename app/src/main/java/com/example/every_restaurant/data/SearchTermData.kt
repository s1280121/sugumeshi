package com.example.every_restaurant.data

import java.io.Serializable

//検索データ
data class Term(
        var lat: Double,
        var lng: Double,
        var keyword: String,
        var range: Int,
): Serializable