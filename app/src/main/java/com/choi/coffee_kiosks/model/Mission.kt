package com.choi.coffee_kiosks.model

data class Mission(
    val _id: Value,
    val name: Value,
    val missionDetail: Value,
    val difficulty: Value,
    val isSolved: Value,
)

data class Value(
    val integerValue: Int?,
    val stringValue: String?,
    val booleanValue: Boolean?,
)

data class FirebaseResponse(
    val documents: List<Document>
)

data class Document(
    val name:String,
    val fields:Mission,
    val createTime:String,
    val updateTime:String,

)
