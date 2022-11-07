package com.example.shoppingapp_04.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp_04.data.model.CartItem2
import com.example.shoppingapp_04.data.model.ShopItem
import com.example.shoppingapp_04.domain.usecase.CartUseCase
import com.example.shoppingapp_04.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel  @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val wishlistUseCase: WishlistUseCase
) : ViewModel() {

    fun saveToCart(cartItem2: CartItem2) = viewModelScope.launch(Dispatchers.IO) {
        cartUseCase.addToCartItem(cartItem2)
    }

    fun addToWishlist(shopItem: ShopItem) = viewModelScope.launch(Dispatchers.IO) {
        wishlistUseCase.addToWishlist(shopItem)
    }

    fun removeFromWishlist(shopItem: ShopItem) = viewModelScope.launch(Dispatchers.IO) {
        wishlistUseCase.deleteWishlist(shopItem)
    }

}