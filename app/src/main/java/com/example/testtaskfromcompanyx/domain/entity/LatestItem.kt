package com.example.testtaskfromcompanyx.domain.entity

data class LatestItem(
    val image: String,
    val category: String,
    val name: String,
    val prise: String
) : ListItem
