package com.example.testtaskfromcompanyx.domain.usecase

import com.example.testtaskfromcompanyx.domain.repository.NetworkRepository
import javax.inject.Inject

class GetListBrandUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    fun getListBrand() = repository.getBrand()
}