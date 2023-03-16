package com.example.lab2.domain

data class UserChoice(

    val flags: String,
    val figures: String,
    var id: Int = UNDEFINED_ID
) {

    companion object {

        const val UNDEFINED_ID = 0
    }
}
