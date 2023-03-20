package com.example.testtaskfromcompanyx.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.testtaskfromcompanyx.domain.entity.ListItem
import com.example.testtaskfromcompanyx.presentation.adapterdelegates.PageOneScreenDelegates
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class HorizontalAdapter : AsyncListDifferDelegationAdapter<ListItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(PageOneScreenDelegates.latestProductDelegate())
            .addDelegate(PageOneScreenDelegates.flashSaleProductDelegate)
            .addDelegate(PageOneScreenDelegates.brandProductDelegate)
            .addDelegate(PageOneScreenDelegates.latestProgressDelegate)
            .addDelegate(PageOneScreenDelegates.flashSaleProgressDelegate)
            .addDelegate(PageOneScreenDelegates.brandProgressDelegate)
    }

    private companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}