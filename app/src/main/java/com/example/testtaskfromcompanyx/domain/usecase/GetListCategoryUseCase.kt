package com.example.testtaskfromcompanyx.domain.usecase

import com.example.testtaskfromcompanyx.domain.repository.NetworkRepository
import javax.inject.Inject

class GetListCategoryUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    fun getListCategory() = repository.getCategory()
}