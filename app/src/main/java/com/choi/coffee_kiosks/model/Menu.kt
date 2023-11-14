package com.choi.coffee_kiosks.model

data class Menu (
    val name : String,
    val price : String,
    val type : Type,
    val image : Int,
)

enum class Type {
    COFFEE, TEA, ADE, NON_COFFEE, JUICE, DESSERT
}
