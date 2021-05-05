package com.example.reahometime

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.reahometime.api.HometimeApi
import com.example.reahometime.api.Repository
import com.example.reahometime.data.Hometime
import com.squareup.inject.assisted.Assisted
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        repository.getDeviceToken()
    }

    var deviceToken = repository.deviceToken

    var hometimesNorth = repository.hometimesNorth
    var hometimesSouth = repository.hometimesSouth

    /**
     * Request hometimes api by stopId.
     */
    fun getHometimes(stopId: Int) {
        Log.d("chadpulling", "pulling data..")
        repository.getHometimes(stopId)
    }

    /**
     * Reset both hometime list as null
     */
    fun clearHometimes() {
        hometimesNorth.postValue(null)
        hometimesSouth.postValue(null)
    }

}