package com.choi.coffee_kiosks.rest

import com.choi.coffee_kiosks.model.FirebaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetMissionService {
    @GET("projects/kioskmanagerpj/databases/(default)/documents/mission_collections")
    suspend fun getMission() : Response<FirebaseResponse>
}