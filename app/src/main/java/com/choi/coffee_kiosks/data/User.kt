package com.choi.coffee_kiosks.data

import java.util.UUID

data class User(
    val uuid: UUID,
    val phoneNumber: String,
    val stampCount: Int,
    val rewardPoint: Long,
    val solvedMissions: List<Mission>,
)
