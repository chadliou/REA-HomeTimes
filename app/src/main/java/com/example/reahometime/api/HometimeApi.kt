package com.example.reahometime.api

import com.example.reahometime.data.HometimeResponse
import com.example.reahometime.data.TokenResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HometimeApi {

    //Get device token
    @GET("GetDeviceToken/?aid=TTIOSJSON&devInfo=HomeTime")
    fun getDeviceToken(): Call<TokenResponse>

    @GET("GetNextPredictedRoutesCollection/{STOP_ID}/78/false/?aid=TTIOSJSON&cid=2")
    fun getHometimes(
        @Path("STOP_ID") stopId: Int,
        @Query("tkn") token: String
    ): Call<HometimeResponse>

    companion object {

        private const val BASE_URL = "https://ws3.tramtracker.com.au/TramTracker/RestService/"

        fun create(): HometimeApi {
            val logger = HttpLoggingInterceptor().apply { level =
                HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HometimeApi::class.java)
        }
    }
}