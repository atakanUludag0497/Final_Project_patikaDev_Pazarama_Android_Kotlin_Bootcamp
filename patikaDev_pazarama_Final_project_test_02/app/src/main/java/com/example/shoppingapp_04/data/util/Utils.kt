package com.example.shoppingapp_04.data.util

import com.example.shoppingapp_04.data.model.ValidationResult

object Utils {
    fun formatPrice(price : String): String {
        return String.format("%.2f", price.toDouble())
    }
    fun validateLoginRequest(username: String,password: String) : ValidationResult {
        if (username.isBlank() && password.isBlank()) return ValidationResult(false,"Username and password cannot be blank")
        if (username.isBlank()) return ValidationResult(false,"Username cannot be blank")
        if (password.isBlank()) return ValidationResult(false,"Password cannot be blank")
        return ValidationResult(true)
    }
}