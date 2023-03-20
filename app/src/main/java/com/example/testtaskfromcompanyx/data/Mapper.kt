package com.example.testtaskfromcompanyx.data

import com.example.testtaskfromcompanyx.data.db.UserDBModel
import com.example.testtaskfromcompanyx.data.network.model.FlashSaleDto
import com.example.testtaskfromcompanyx.data.network.model.LatestDto
import com.example.testtaskfromcompanyx.domain.entity.FlashSaleItem
import com.example.testtaskfromcompanyx.domain.entity.LatestItem
import com.example.testtaskfromcompanyx.domain.entity.User
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapEntityToDbModel(user: User) = UserDBModel(
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email
    )

    fun mapDbModelToEntity(dbModel: UserDBModel) = User(
        firstName = dbModel.firstName,
        lastName = dbModel.lastName,
        email = dbModel.email
    )

    fun mapLatestEntityToLatestModel(latestEntity: LatestItem) = LatestDto(
        category = latestEntity.category,
        image_url = latestEntity.image.toString(),
        name = latestEntity.name,
        price = latestEntity.prise.toInt()
    )

    fun mapLatestModelToLatestEntity(latestModel: LatestDto) = LatestItem(
        category = latestModel.category,
        image = latestModel.image_url,
        name = latestModel.name,
        prise = latestModel.price.toString()
    )

    fun mapFlashSaleEntityToFlashSaleModel(flashSaleEntity: FlashSaleItem) = FlashSaleDto(
        category = flashSaleEntity.category,
        discount = flashSaleEntity.discount.toInt(),
        image_url = flashSaleEntity.image.toString(),
        name = flashSaleEntity.name,
        price = flashSaleEntity.prise.toDouble()
    )

    fun mapFlashSaleModelToFlashSaleEntity(flashSaleMadel: FlashSaleDto) = FlashSaleItem(
        category = flashSaleMadel.category,
        discount = flashSaleMadel.discount.toString(),
        image = flashSaleMadel.image_url,
        name = flashSaleMadel.name,
        prise = flashSaleMadel.price.toString()
    )
}