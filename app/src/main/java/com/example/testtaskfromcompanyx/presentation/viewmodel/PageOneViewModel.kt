package com.example.testtaskfromcompanyx.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.domain.entity.*
import com.example.testtaskfromcompanyx.domain.usecase.GetListBrandUseCase
import com.example.testtaskfromcompanyx.domain.usecase.GetListCategoryUseCase
import com.example.testtaskfromcompanyx.domain.usecase.GetListFlashSaleUseCase
import com.example.testtaskfromcompanyx.domain.usecase.GetListLatestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class PageOneViewModel @Inject constructor(
    private val getListCategoryUseCase: GetListCategoryUseCase,
    private val getListLatestUseCase: GetListLatestUseCase,
    private val getListFlashSaleUseCase: GetListFlashSaleUseCase,
    private val getListBrandUseCase: GetListBrandUseCase,
    private val application: Application
) : ViewModel() {

    private val _getCategory = MutableLiveData<List<ListItem>>()
    val getCategory: LiveData<List<ListItem>>
        get() = _getCategory

    private val _getItems = MutableLiveData<List<ListItem>>()
    val getItems: LiveData<List<ListItem>>
        get() = _getItems

    private val _getSearchItems = MutableLiveData<List<ListItem>>()
    val getSearchItems: LiveData<List<ListItem>>
        get() = _getSearchItems

    init {
        _getCategory.value = getCategoryItems()
        viewModelScope.launch {
            _getItems.value = getLoaders()
            delay(2000)
            _getItems.value = getItems()
        }
    }

    private fun getCategoryItems(): List<ListItem> {
        return getListCategoryUseCase.getListCategory()
    }

    private fun getLoaders(): List<ListItem> {
        return listOf(
            ProductHorizontalItem(
                title = application.resources.getString(R.string.latest_deals),
                products = (0..3).map { ProgressThinItem }
            ),
            ProductHorizontalItem(
                title = application.resources.getString(R.string.flash_Sale),
                products = (0..1).map { ProgressWideItem }
            ),
            ProductHorizontalItem(
                title = application.resources.getString(R.string.brands),
                products = (0..3).map { ProgressBrandItem }
            )
        )
    }

    suspend fun getSearchItems(query: String) {
        val responseLatest = getListLatestUseCase.getListLatest()?.filter {
            it.name.toLowerCase(Locale.getDefault())
                .contains(query.toLowerCase(Locale.getDefault()))
        }
        val responseFlashSale = getListFlashSaleUseCase.getListFlashSale()?.filter {
            it.name.toLowerCase(Locale.getDefault())
                .contains(query.toLowerCase(Locale.getDefault()))
        }

        val latest = responseLatest?.map {
            LatestItem(
                category = it.category,
                image = it.image,
                name = it.name,
                prise = it.prise
            )
        }
        val flashSale = responseFlashSale?.map {
            FlashSaleItem(
                category = it.category,
                image = it.image,
                name = it.name,
                prise = it.prise,
                discount = it.discount
            )
        }
        _getSearchItems.postValue(
            listOf(
                ProductHorizontalItem(
                    title = application.resources.getString(R.string.latest_deals),
                    products = latest
                ),
                ProductHorizontalItem(
                    title = application.resources.getString(R.string.flash_Sale),
                    products = flashSale
                ),
                ProductHorizontalItem(
                    title = application.resources.getString(R.string.brands),
                    products = getListBrandUseCase.getListBrand()
                )
            )
        )
    }

    private suspend fun getItems(): List<ListItem> {
        val responseLatest = getListLatestUseCase.getListLatest()
        val responseFlashSale = getListFlashSaleUseCase.getListFlashSale()

        val latest = responseLatest?.map {
            LatestItem(
                category = it.category,
                image = it.image,
                name = it.name,
                prise = it.prise
            )
        }
        val flashSale = responseFlashSale?.map {
            FlashSaleItem(
                category = it.category,
                image = it.image,
                name = it.name,
                prise = it.prise,
                discount = it.discount
            )
        }
        return listOf(
            ProductHorizontalItem(
                title = application.resources.getString(R.string.latest_deals),
                products = latest
            ),
            ProductHorizontalItem(
                title = application.resources.getString(R.string.flash_Sale),
                products = flashSale
            ),
            ProductHorizontalItem(
                title = application.resources.getString(R.string.brands),
                products = getListBrandUseCase.getListBrand()
            )
        )
    }
}