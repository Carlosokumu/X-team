package com.example.x_team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainViewModel(val apiservice: ApiService):ViewModel() {
    @JvmName("getRestaurants1")
    fun getBlog()= liveData(Dispatchers.IO) {
        //restaurants.value?.data
        emit(ApiModel.loading(null))
        try {
            emit(ApiModel.success(data = apiservice.getBlog()))
        }
        catch (exception: Exception){
            emit(ApiModel.error(data = null,message = "Couldn't refresh"))
        }

    }

}