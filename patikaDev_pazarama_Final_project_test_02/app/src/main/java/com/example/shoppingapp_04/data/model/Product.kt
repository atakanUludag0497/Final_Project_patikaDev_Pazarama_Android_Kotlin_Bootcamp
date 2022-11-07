package com.example.shoppingapp_04.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int,
)