package com.choi.coffee_kiosks.util.common

import androidx.fragment.app.Fragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.model.Menu
import com.choi.coffee_kiosks.model.Type
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.textview.MaterialTextView


const val INTERVAL_TIME = 250L
const val CLICK_TAG = "ON_CLICK_TAG"
const val LOG_TAG = "KIOSKS"

const val BASE_URL = "https://api.odcloud.kr/api/"
const val API_KEY =
    "Infuser sUXB37g6xS7shINsa1ElGsLUCYuBrRRDitqWNQzYYVv3CBOLdHi+MCivy40pwovZukCMMKlb2ufKV0w0AdV+Tg=="


// 키오스크 설치 구역 위치 좌표
val places = hashMapOf(
    1 to LatLng(37.4567667, 126.8954005),
    2 to LatLng(37.4501557, 126.8978137),
    3 to LatLng(37.4570918, 126.8869474),
    4 to LatLng(37.4675303, 126.89437),
    5 to LatLng(37.4768503, 126.8918196),
    6 to LatLng(37.4511867, 126.9140346),
)

val telephoneNum = listOf<String>(
    "02-2627-1005",
    "02-2627-2355",
    "02-2627-2356",
    "02-2627-2355",
    "02-2627-2255",
    "02-2627-2255",
)

val coffees = listOf<Menu>(
    Menu("아메리카노", 1500L, Type.COFFEE, R.drawable.americano),
    Menu("카페라떼", 2900L, Type.COFFEE, R.drawable.caffelatte),
    Menu("바닐라라떼", 3300L, Type.COFFEE, R.drawable.vanilla_latte),
    Menu("헤이즐넛라떼", 3300L, Type.COFFEE, R.drawable.hazelnuts_latte),
    Menu("더치커피", 3300L, Type.COFFEE, R.drawable.dutch),
    Menu("더치라떼", 3800L, Type.COFFEE, R.drawable.dutch_latte),
)

val juices = listOf<Menu>(
    Menu("키위주스", 3800L, Type.JUICE, R.drawable.kiwi_juice),
    Menu("복숭아주스", 3800L, Type.JUICE, R.drawable.peach_juice),
    Menu("샤인머스켓주스", 3800L, Type.JUICE, R.drawable.shine_kale_juice),
    Menu("오렌지 당근주스", 3800L, Type.JUICE, R.drawable.orange_carrot_juice),
)

val teas = listOf<Menu>(
    Menu("페퍼민트", 2500L, Type.TEA, R.drawable.pepper_mint),
    Menu("캐모마일", 2500L, Type.TEA, R.drawable.chamomile),
    Menu("로즈마리", 2500L, Type.TEA, R.drawable.rosemary),
    Menu("홍차", 2500L, Type.TEA, R.drawable.earl_gery),
    Menu("복숭아티", 3000L, Type.TEA, R.drawable.peach_tea),
)

val ades = listOf<Menu>(
    Menu("레몬에이드", 3500L, Type.ADE, R.drawable.lemonade),
    Menu("자몽에이드", 3500L, Type.ADE, R.drawable.jamongade),
    Menu("유자에이드", 3500L, Type.ADE, R.drawable.citronade),
    Menu("청포도에이드", 3500L, Type.ADE, R.drawable.greengrapeade),
)

val nonCoffees = listOf<Menu>(
    Menu("그린티라떼", 3500L, Type.NON_COFFEE, R.drawable.green_tea_latte),
    Menu("곡물라떼", 3300L, Type.NON_COFFEE, R.drawable.grain_latte),
    Menu("고구마라떼", 3500L, Type.NON_COFFEE, R.drawable.sweet_potato_latte),
)

val desserts = listOf<Menu>(
    Menu("마카롱", 1800L, Type.DESSERT, R.drawable.macarong),
    Menu("와플", 3000L, Type.DESSERT, R.drawable.waffle),
    Menu("롤케잌", 4500L, Type.DESSERT, R.drawable.roll_cake),
    Menu("카스테라", 5000L, Type.DESSERT, R.drawable.castella),
)

const val OPTIONS = "options"



