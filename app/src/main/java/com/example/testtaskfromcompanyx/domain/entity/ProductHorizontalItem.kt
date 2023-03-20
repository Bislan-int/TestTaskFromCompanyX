package com.example.testtaskfromcompanyx.domain.entity

data class ProductHorizontalItem(
    val title: String,
    val products: List<ListItem>?
) : ListItem
