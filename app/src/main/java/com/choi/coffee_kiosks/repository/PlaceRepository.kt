package com.choi.coffee_kiosks.repository

import com.choi.coffee_kiosks.entity.Response

interface PlaceRepository {

    suspend fun getPlaces() : Response
}