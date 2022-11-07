package com.example.shoppingapp_04.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp_04.data.repository.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    fun setOnBoardingStatus() {
        viewModelScope.launch {
            dataStoreManager.setOnBoardingVisible(true)
        }
    }
}