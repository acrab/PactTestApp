package com.example.android.pacttestapp

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ShoutCloudService {

    @POST("/V1/SHOUT")
    @Headers("Content-Type: application/json")
    fun shout(@Body INPUT: ShoutCloudRequest): Observable<ShoutCloudResponse>

    companion object {
        fun create(baseUrL:String = "HTTP://API.SHOUTCLOUD.IO"): ShoutCloudService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(baseUrL)
                .build()

            return retrofit.create(ShoutCloudService::class.java)
        }
    }
}

data class ShoutCloudRequest(val INPUT: String)

data class ShoutCloudResponse(val INPUT: String, val OUTPUT:String)