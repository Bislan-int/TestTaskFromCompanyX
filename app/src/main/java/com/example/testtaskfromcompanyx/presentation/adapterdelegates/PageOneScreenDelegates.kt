package com.example.testtaskfromcompanyx.presentation.adapterdelegates

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.databinding.*
import com.example.testtaskfromcompanyx.domain.entity.*
import com.example.testtaskfromcompanyx.presentation.adapter.HorizontalAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object PageOneScreenDelegates {

    val categoryItemDelegate =
        adapterDelegateViewBinding<CategoryItem, ListItem, ProductCategoryItemBinding>(
            { inflater, container ->
                ProductCategoryItemBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.imageCategory.setImageResource(item.image)
                binding.nameCategory.text = item.name
            }
        }

    val itemHorizontalDelegate =
        adapterDelegateViewBinding<ProductHorizontalItem, ListItem, ItemProductHorizontalBinding>(
            { inflater, container ->
                ItemProductHorizontalBinding.inflate(inflater, container, false)
            }
        ) {
            val adapter = HorizontalAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.products
            }
        }

    val latestProgressDelegate =
        adapterDelegateViewBinding<ProgressThinItem, ListItem, ItemProgressLatestBinding>(
            { inflater, container ->
                ItemProgressLatestBinding.inflate(inflater, container, false)
            }
        ) {}

    fun latestProductDelegate() =
        adapterDelegateViewBinding<LatestItem, ListItem, LatestItemBinding>(
            { inflater, container ->
                LatestItemBinding.inflate(inflater, container, false)
            }
        ) {
            binding.root.setOnClickListener {

            }
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .transform(
                        CenterCrop(),
                        RoundedCorners(
                            resources.getDimensionPixelOffset(R.dimen.image_corner_radius)
                        )
                    )
                    .into(binding.imageLatestItem)
                binding.categoryProductLatestItem.text = item.category
                binding.nameProductLatestItem.text = item.name
                binding.priseProductLatestItem.text =
                    resources.getString(R.string.ic_dollar) + item.prise
            }
        }

    val flashSaleProgressDelegate =
        adapterDelegateViewBinding<ProgressWideItem, ListItem, ItemProgressFlashSaleBinding>(
            { inflater, container ->
                ItemProgressFlashSaleBinding.inflate(inflater, container, false)
            }
        ) {}

    val flashSaleProductDelegate =
        adapterDelegateViewBinding<FlashSaleItem, ListItem, FlashSaleItemBinding>(
            { inflater, container ->
                FlashSaleItemBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .transform(
                        CenterCrop(),
                        RoundedCorners(
                            resources.getDimensionPixelOffset(R.dimen.image_corner_radius)
                        )
                    )
                    .into(binding.imageView)
                binding.tvDiscount.text = item.discount + resources.getString(R.string.ic_present)
                binding.tvCategory.text = item.category
                binding.tvName.text = item.name
                binding.tvPrise.text = resources.getString(R.string.ic_dollar) + item.prise
            }
        }

    val brandProgressDelegate =
        adapterDelegateViewBinding<ProgressBrandItem, ListItem, ItemProgressBrandBinding>(
            { inflater, container ->
                ItemProgressBrandBinding.inflate(inflater, container, false)
            }
        ) {}

    val brandProductDelegate =
        adapterDelegateViewBinding<BrandItem, ListItem, BrandItemBinding>(
            { inflater, container ->
                BrandItemBinding.inflate(inflater, container, false)
            }
        ) {
            bind { binding.imageView.setImageResource(item.image) }
        }
}