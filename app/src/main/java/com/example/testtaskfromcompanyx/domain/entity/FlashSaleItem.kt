package com.example.testtaskfromcompanyx.domain.entity

data class FlashSaleItem(
    val image: String,
    val discount: String,
    val category: String,
    val name: String,
    val prise: String
) : ListItem
