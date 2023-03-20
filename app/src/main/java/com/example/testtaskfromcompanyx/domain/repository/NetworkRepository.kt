package com.example.testtaskfromcompanyx.domain.repository

import androidx.lifecycle.LiveData
import com.example.testtaskfromcompanyx.domain.entity.BrandItem
import com.example.testtaskfromcompanyx.domain.entity.CategoryItem
import com.example.testtaskfromcompanyx.domain.entity.FlashSaleItem
import com.example.testtaskfromcompanyx.domain.entity.LatestItem

interface NetworkRepository {

    suspend fun getLatest(): List<LatestItem>?
    suspend fun getFlashSale(): List<FlashSaleItem>?
    fun getBrand(): List<BrandItem>
    fun getCategory(): List<CategoryItem>
}