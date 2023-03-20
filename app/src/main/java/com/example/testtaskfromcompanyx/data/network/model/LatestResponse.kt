package com.example.testtaskfromcompanyx.data.network.model

import com.example.testtaskfromcompanyx.domain.entity.ListItem

data class LatestResponse(
    val latest: List<LatestDto>
) : ListItem