package com.example.shoppingapp_04.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp_04.data.model.ShopItem
import com.example.shoppingapp_04.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistUseCase: WishlistUseCase
) : ViewModel() {

    fun getWishlist() = liveData {
        wishlistUseCase.getWishlist().collect{
            emit(it)
        }
    }

    fun deleteWishlist(shopItem: ShopItem) = viewModelScope.launch(Dispatchers.IO) {
        wishlistUseCase.deleteWishlist(shopItem)
    }

    fun clearWishlist() = viewModelScope.launch(Dispatchers.IO) {
        wishlistUseCase.clearWishlist()
    }


}