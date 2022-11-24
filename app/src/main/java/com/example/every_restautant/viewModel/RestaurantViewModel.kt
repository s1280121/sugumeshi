package com.example.every_restaurant.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.every_restautant.data.Restaurant
import com.example.every_restautant.data.Term
import kotlinx.coroutines.launch


class ReataurantViewModel : ViewModel() {
    val restaurantDataList = MutableLiveData<List<Restaurant>>()

    fun getReataurantData(SEARCH_TERM: Term) {
        viewModelScope.launch {
            val response = ReataurantDataRepository.instance.getReataurantData(
                SEARCH_TERM.lat.toString(),
                SEARCH_TERM.lng.toString(),
                SEARCH_TERM.keyword,
                SEARCH_TERM.range.toString()
            )
            if (response.isSuccessful) {
                val restaurantList = response.body()?.results?.shop
                if (restaurantList != null) {
                    restaurantDataList.postValue(restaurantList)
                }
            } else {
            }
        }
    }
}
