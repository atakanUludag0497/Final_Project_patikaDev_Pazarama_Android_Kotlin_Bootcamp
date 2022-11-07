package com.example.shoppingapp_04.domain.usecase

import com.example.shoppingapp_04.data.model.Category
import com.example.shoppingapp_04.data.model.Shop
import com.example.shoppingapp_04.data.util.Resource
import com.example.shoppingapp_04.domain.repository.ShopRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend fun getAllProducts() : Resource<Shop> {
        return repository.getAllProducts()
    }
    suspend fun getAllCategories() : Resource<Category>{
        return repository.getAllCategories()
    }
    suspend fun getCategoryProducts(category : String) : Resource<Shop>{
        return repository.getCategoryProducts(category)
    }
}