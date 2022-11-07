package com.example.shoppingapp_04.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp_04.data.model.User
import com.example.shoppingapp_04.data.util.Resource
import com.example.shoppingapp_04.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val theUser : MutableLiveData<Resource<User>> = MutableLiveData()

    fun updateUser(id : Int,user: User) = viewModelScope.launch(Dispatchers.IO) {
        theUser.postValue(Resource.Loading())
        try {
            val apiResult = profileUseCase.updateUser(id,user)
            theUser.postValue(apiResult)
        }catch (e : Exception){
            theUser.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }
}