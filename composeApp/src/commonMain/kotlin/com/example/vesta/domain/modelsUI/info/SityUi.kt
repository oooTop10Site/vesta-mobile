package com.example.vesta.data.models.info

import kotlinx.serialization.Serializable

@Serializable
data class SityUi(
    val storeId: Int,
    val name: String,
    val url: String,
    val ssl: String,
    val phone: String
){
    companion object{
        val empty = SityUi(
            storeId = 0,
            name = "",
            url = "",
            ssl = "",
            phone = ""
        )
    }
}

