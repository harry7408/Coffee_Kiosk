package com.choi.coffee_kiosks.util.common

import com.google.android.gms.maps.model.LatLng


const val INTERVAL_TIME = 250L
const val CLICK_TAG = "ON_CLICK_TAG"
const val LOG_TAG="KIOSKS"

const val BASE_URL = "https://api.odcloud.kr/api/"
const val API_KEY = "Infuser sUXB37g6xS7shINsa1ElGsLUCYuBrRRDitqWNQzYYVv3CBOLdHi+MCivy40pwovZukCMMKlb2ufKV0w0AdV+Tg=="


// 키오스크 설치 구역 위치 좌표
val places = hashMapOf(
    1 to LatLng(37.4567667, 126.8954005),
    2 to LatLng(37.4501557, 126.8978137),
    3 to LatLng(37.4570918, 126.8869474),
    4 to LatLng(37.4675303, 126.89437),
    5 to LatLng(37.4768503, 126.8918196),
    6 to LatLng(37.4511867, 126.9140346),
)
