package com.example.shoppingapp_04.domain.usecase

import com.example.shoppingapp_04.data.model.ShopItem
import com.example.shoppingapp_04.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend fun addToWishlist(shopItem: ShopItem){
        repository.addToWishlist(shopItem)
    }
    suspend fun deleteWishlist(shopItem: ShopItem){
        return repository.deleteWishlistItem(shopItem)
    }
    fun getWishlist() : Flow<List<ShopItem>> {
        return repository.getWishlistItems()
    }
    suspend fun clearWishlist(){
        return repository.clearWishlist()
    }
}