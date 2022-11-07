package com.example.shoppingapp_04.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp_04.data.model.CartItem2
import com.example.shoppingapp_04.data.util.Utils
import com.example.shoppingapp_04.domain.usecase.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    val totalItems : MutableLiveData<Int> = MutableLiveData()
    val totalItemsPrice : MutableLiveData<Double> = MutableLiveData()

    fun getCartItems() = liveData {
        cartUseCase.getCartItems().collect{
            emit(it)
            computeTotal(it)
        }
    }

    fun computeTotal(cartItems : List<CartItem2>) = viewModelScope.launch(Dispatchers.IO){
        var price = 0.00
        cartItems.forEach { product ->
            price += product.price.toDouble()
        }
        totalItemsPrice.postValue(price)
        totalItems.postValue(cartItems.size)
    }

    fun deleteCart(cartItem2: CartItem2) = viewModelScope.launch(Dispatchers.IO) {
        cartUseCase.deleteCartItem(cartItem2)
    }

    fun clearCart() = viewModelScope.launch(Dispatchers.IO) {
        cartUseCase.clearCart()
    }

    fun increment(cartItem: CartItem2){
        updateProductInCart(quantity = cartItem.quantity + 1, price = cartItem.price.toDouble() + cartItem.pricePerItem,cartItem)
    }

    fun decrement(cartItem: CartItem2){
        if (cartItem.quantity > 1){
            updateProductInCart(quantity = cartItem.quantity -1, price = cartItem.price.toDouble() - cartItem.pricePerItem,cartItem)
        }
    }

    private fun updateProductInCart(quantity: Int, price: Double, cartItem: CartItem2) = viewModelScope.launch(
        Dispatchers.IO
    ){
        val copy = cartItem.copy(price = Utils.formatPrice(price.toString()), quantity = quantity)
        cartUseCase.updateCartItem(copy)
    }
}