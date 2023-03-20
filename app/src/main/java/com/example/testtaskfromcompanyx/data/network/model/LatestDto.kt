package com.example.testtaskfromcompanyx.data.network.model

import com.example.testtaskfromcompanyx.domain.entity.ListItem

data class LatestDto(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) : ListItem