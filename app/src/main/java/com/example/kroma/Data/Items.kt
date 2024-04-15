package com.example.kroma.Data

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class ProductResponse(
    val products: List<Item>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Item(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)

data class ChipData(
    val categories: List<String>,
    val selected: Boolean,
)