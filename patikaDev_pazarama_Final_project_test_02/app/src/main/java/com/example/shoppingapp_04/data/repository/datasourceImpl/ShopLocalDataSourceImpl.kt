package com.example.shoppingapp_04.data.repository.datasourceImpl

import com.example.shoppingapp_04.data.db.ShopDAO
import com.example.shoppingapp_04.data.model.CartItem2
import com.example.shoppingapp_04.data.model.ShopItem
import com.example.shoppingapp_04.data.repository.datasource.ShopLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopLocalDataSourceImpl @Inject constructor(
    private val shopDAO: ShopDAO
)  : ShopLocalDataSource {
    override suspend fun addToCart(cartItem2: CartItem2) {
        return shopDAO.addToCart(cartItem2)
    }
    override fun getCartItems(): Flow<List<CartItem2>> {
        return shopDAO.cartItems()
    }
    override suspend fun updateCartItems(cartItem2: CartItem2) {
        return shopDAO.updateCart(cartItem2)
    }
    override suspend fun deleteCartItems(cartItem2: CartItem2) {
        return shopDAO.deleteCart(cartItem2)
    }
    override suspend fun clearCart() {
        return shopDAO.clearAll()
    }
    override suspend fun addToWishlist(shopItem: ShopItem) {
        return shopDAO.addToWishlist(shopItem)
    }
    override fun getWishlistItems(): Flow<List<ShopItem>> {
        return shopDAO.wishlistItems()
    }
    override suspend fun deleteWishlistItem(shopItem: ShopItem) {
        return shopDAO.deleteWishlist(shopItem)
    }
    override suspend fun clearWishlist() {
        return shopDAO.clearWishlist()
    }
}