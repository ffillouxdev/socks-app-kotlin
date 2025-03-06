package com.example.fillouxproject.datas

import androidx.compose.runtime.MutableState
import java.io.Serializable

data class Product(
    val name: String,
    val purchasableDate: String,
    val color: String,
    val nativeCountry: String,
    val favorite: Boolean,
    val productType: MutableState<ProductType>
): Serializable
