package com.choi.coffee_kiosks.network

import com.choi.coffee_kiosks.entity.Response
import retrofit2.Call
import retrofit2.http.GET


interface KioskPositionService {

    @GET("15115695/v1/uddi:5dccf336-7290-4e5f-acbe-aa386ac9a8ba")
    fun getInfo() : Call<Response>
}