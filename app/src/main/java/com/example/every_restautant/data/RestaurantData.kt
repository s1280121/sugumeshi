package com.example.every_restautant.data

import java.io.Serializable

//https://webservice.recruit.co.jp/doc/hotpepper/reference.html

data class RestaurantData(
    var results: ResultsData,
)

data class ResultsData(
    var shop: List<Restaurant>,
)

data class Restaurant(
    var name: String,
    var name_kana: String,
    var access: String,
    var logo_image: String,
    var photo: PhotoData,
    var genre: GenreData,
    var address: String,
    var open: String,
    var close: String,
    var catch: String,
    var card: String,
    var non_smoking: String,
    var parking: String,
    var budget: BudgetData
) : Serializable

data class GenreData(
    var name: String,
) : Serializable

data class PhotoData(
    var mobile: SizeData,
    var pc: SizeData,
) : Serializable

data class SizeData(
    var l: String,
) : Serializable

data class BudgetData(
    var average: String,
) : Serializable
