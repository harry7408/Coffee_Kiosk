package com.choi.coffee_kiosks.data

data class Menu (
    val name : String,
    val price : Long,
    val type : Type,
    val image : Int,
)

enum class Type {
    COFFEE, TEA, ADE, NON_COFFEE, JUICE, DESSERT
}
