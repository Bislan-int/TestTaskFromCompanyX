package com.example.testtaskfromcompanyx.data.network

import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.data.Mapper
import com.example.testtaskfromcompanyx.domain.entity.BrandItem
import com.example.testtaskfromcompanyx.domain.entity.CategoryItem
import com.example.testtaskfromcompanyx.domain.entity.FlashSaleItem
import com.example.testtaskfromcompanyx.domain.entity.LatestItem
import com.example.testtaskfromcompanyx.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mapper: Mapper
) : NetworkRepository {

    override suspend fun getLatest(): List<LatestItem>? {
        return api.getLatest()?.latest?.map { mapper.mapLatestModelToLatestEntity(it) }
    }

    override suspend fun getFlashSale(): List<FlashSaleItem>? {
        return api.getFlashSale()?.flash_sale?.map { mapper.mapFlashSaleModelToFlashSaleEntity(it) }
    }

    override fun getBrand(): List<BrandItem> {
        return (0..4).map { BrandItem(R.drawable.photo_brands) }
    }

    override fun getCategory(): List<CategoryItem> {
        return (0..7).map { CategoryItem(image = R.drawable.ic_category_phone, name = "Phone") }
    }
}