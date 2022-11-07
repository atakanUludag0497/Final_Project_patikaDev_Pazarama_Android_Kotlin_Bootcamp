package com.example.shoppingapp_04.domain.usecase

import com.example.shoppingapp_04.data.model.User
import com.example.shoppingapp_04.data.util.Resource
import com.example.shoppingapp_04.domain.repository.ShopRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: ShopRepository) {
    suspend fun getUser(id : Int) : Resource<User> {
        return repository.getUser(id)
    }
    suspend fun updateUser(id : Int,user: User): Resource<User> {
        return repository.updateUser(id,user)
    }
}