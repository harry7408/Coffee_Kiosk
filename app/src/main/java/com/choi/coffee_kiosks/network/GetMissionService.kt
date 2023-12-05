package com.choi.coffee_kiosks.network

import com.choi.coffee_kiosks.data.FirebaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetMissionService {
    @GET("projects/kioskmanagerpj/databases/(default)/documents/easy_missions")
    suspend fun getEasyMission() : Response<FirebaseResponse>

    @GET("projects/kioskmanagerpj/databases/(default)/documents/normal_missions")
    suspend fun getNormalMission() : Response<FirebaseResponse>

    @GET("projects/kioskmanagerpj/databases/(default)/documents/hard_missions")
    suspend fun getHardMission() : Response<FirebaseResponse>
}