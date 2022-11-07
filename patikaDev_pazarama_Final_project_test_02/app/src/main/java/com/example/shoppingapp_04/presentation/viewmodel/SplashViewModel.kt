package com.example.shoppingapp_04.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shoppingapp_04.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    sharedPreference: SharedPreference
) : ViewModel() {
    val loggedIn : Boolean = sharedPreference.userIsLoggedIn()
}