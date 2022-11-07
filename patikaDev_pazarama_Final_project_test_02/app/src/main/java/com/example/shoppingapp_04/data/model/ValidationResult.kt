package com.example.shoppingapp_04.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)