package com.choi.coffee_kiosks.repository

import com.choi.coffee_kiosks.entity.Response
import com.choi.coffee_kiosks.network.KioskPositionService

class PlaceRepositoryImpl(private val service: KioskPositionService) :
    PlaceRepository {
    override suspend fun getPlaces(): Response {
        return service.getInfo().body()!!
    }

}