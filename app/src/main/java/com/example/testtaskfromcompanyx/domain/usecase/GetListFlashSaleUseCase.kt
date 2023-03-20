package com.example.testtaskfromcompanyx.domain.usecase

import com.example.testtaskfromcompanyx.domain.repository.NetworkRepository
import javax.inject.Inject

class GetListFlashSaleUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    suspend fun getListFlashSale() = repository.getFlashSale()
}