package com.choi.coffee_kiosks.entity

import com.google.gson.annotations.SerializedName


data class Response (
    @SerializedName("data")
    val data: List<KioskPosition>,
)


data class KioskPosition(
    @SerializedName("담당부서")
    val department: String,
    @SerializedName("설치기관")
    val facility: String,
    @SerializedName("설치장소")
    val installedPlace: String,
    @SerializedName("주소")
    val address: String,
    @SerializedName("연번")
    val number: Int,
)
