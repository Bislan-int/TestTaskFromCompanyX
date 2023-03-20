package com.example.testtaskfromcompanyx.data.network.model

data class FlashSaleDto(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)