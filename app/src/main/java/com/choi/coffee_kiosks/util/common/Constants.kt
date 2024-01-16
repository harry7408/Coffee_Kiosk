package com.choi.coffee_kiosks.util.common

import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.entity.Menu
import com.choi.coffee_kiosks.entity.Type
import com.google.android.gms.maps.model.LatLng


const val INTERVAL_TIME = 250L
const val CLICK_TAG = "ON_CLICK_TAG"


const val LOG_TAG = "KIOSKS"
const val BASE_URL = "https://api.odcloud.kr/api/"
const val API_KEY =
    "Infuser sUXB37g6xS7shINsa1ElGsLUCYuBrRRDitqWNQzYYVv3CBOLdHi+MCivy40pwovZukCMMKlb2ufKV0w0AdV+Tg=="

const val FIREBASE_BASE_URL = "https://firestore.googleapis.com/v1/"

// 키오스크 설치 구역 위치 좌표
val places = hashMapOf(
    1 to LatLng(37.4567667, 126.8954005),
    2 to LatLng(37.4501557, 126.8978137),
    3 to LatLng(37.4570918, 126.8869474),
    4 to LatLng(37.4675303, 126.89437),
    5 to LatLng(37.4768503, 126.8918196),
    6 to LatLng(37.4511867, 126.9140346),
)

val composePosition = hashMapOf<String, LatLng>(
    "컴포즈커피 가산백상스타타워점" to LatLng(37.4826204, 126.8776612),
    "컴포즈커피 가산SKV1점" to LatLng(37.4806318, 126.8805702),
    "컴포즈커피 가산디지털단지역점" to LatLng(37.4813244, 126.8837789),
    "컴포즈커피 가산로데오점" to LatLng(37.4803905, 126.8867844),
    "컴포즈커피 가산스카이밸리점" to LatLng(37.4784241, 126.883311),
    "컴포즈커피 가산월드메르디앙점" to LatLng(37.4777126, 126.8853467),
    "컴포즈커피 에이스가산타워점" to LatLng(37.4759689, 126.8801495),
    "컴포즈커피 가산에이스태세라점" to LatLng(37.4725879, 126.8826347),
    "컴포즈커피 독산대륭테크노타운점" to LatLng(37.4667852, 126.886837),
    "컴포즈커피 독산롯데시네마점" to LatLng(37.4693385, 126.8971388),
    "컴포즈커피 정훈단지점" to LatLng(37.4647907, 126.9029142),
    "컴포즈커피 금천롯데캐슬점" to LatLng(37.4599297, 126.8959328),
    "컴포즈커피 시흥현대시장점" to LatLng(37.4594495, 126.9052277),
    "컴포즈커피 금천구청점" to LatLng(37.4552109, 126.8972414),
    "컴포즈커피 시흥사거리점" to LatLng(37.4545181, 126.9009257),
    "컴포즈커피 시흥금천점" to LatLng(37.4476883, 126.9024174),
    "컴포즈커피 시흥은행나무점" to LatLng(37.4508072, 126.9089654),
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
const val HOME_FRAGMENT = "HOME"

// Free Option SharedPref KEYS
const val FREE_OPTIONS = "FREE_OPTIONS"
const val ICE_OPTION = "ICE"
const val SUGAR_OPTION = "SUGAR"
const val DENSITY_OPTION = "DENSITY"

// Non Free Option SharedPref KEYS
const val NON_FREE_OPTIONS = "NON_FREE_OPTIONS"
const val HAZELNUT_OPTIONS = "HAZELNUT"
const val PERL_OPTIONS = "PERL"
const val VANILLA_OPTIONS = "VANILLA"
const val SHOT_OPTIONS = "SHOT"
const val CREAM_OPTIONS = "CREAM"

const val HAZELNUT_PRICE = "HP"
const val PERL_PRICE = "PP"
const val VANILLA_PRICE = "VP"
const val SHOT_PRICE = "SP"
const val CREAM_PRICE = "CP"

const val TOTAL_PRICE_SK = "TOTAL"
const val TOTAL_PRICE = "TOTAL_PRICE"

const val JOIN_KEY="JOIN"
const val READ_KEY="READ"






