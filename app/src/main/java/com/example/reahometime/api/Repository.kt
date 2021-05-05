package com.example.reahometime.api

import androidx.lifecycle.MutableLiveData
import com.example.reahometime.data.Hometime
import com.example.reahometime.data.HometimeResponse
import com.example.reahometime.data.TokenResponse
import com.example.reahometime.data.enum.StopId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: HometimeApi
) {

    var deviceToken = MutableLiveData("") //LiveData from device token
    var hometimesNorth = MutableLiveData<List<Hometime>?>() //LiveData for north hometime list
    var hometimesSouth = MutableLiveData<List<Hometime>?>() //LiveData for south hometime list

    /**
     * Request device token
     */
    fun getDeviceToken() {
        service.getDeviceToken()
            .enqueue(object: Callback<TokenResponse> {
                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>
                ) {
                    response.body()?.let {
                        deviceToken.postValue(it.tokens[0].deviceToken)
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    deviceToken.postValue("")
                }

            })
    }

    /**
     * Request hometime list by stop id
     */
    fun getHometimes(
        stopId: Int
    ) {
        deviceToken.value?.let {
            service.getHometimes(stopId, it)
                .enqueue(object: Callback<HometimeResponse> {
                    override fun onResponse(
                        call: Call<HometimeResponse>,
                        response: Response<HometimeResponse>
                    ) {
                        response.body()?.let {
                            when(stopId) {
                                StopId.NORTH.value -> hometimesNorth.postValue(it.hometimes)
                                StopId.SOUTH.value -> hometimesSouth.postValue(it.hometimes)
                            }
                        }
                    }

                    override fun onFailure(call: Call<HometimeResponse>, t: Throwable) {
                        when(stopId) {
                            StopId.NORTH.value -> hometimesNorth.postValue(null)
                            StopId.SOUTH.value -> hometimesSouth.postValue(null)
                        }
                    }

                })
        }

    }

}