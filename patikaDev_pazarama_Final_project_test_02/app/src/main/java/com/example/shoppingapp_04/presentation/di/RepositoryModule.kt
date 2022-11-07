package com.example.shoppingapp_04.presentation.di

import com.example.shoppingapp_04.data.repository.ShopRepositoryImpl
import com.example.shoppingapp_04.data.repository.datasource.ShopLocalDataSource
import com.example.shoppingapp_04.data.repository.datasource.ShopRemoteDataSource
import com.example.shoppingapp_04.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesShopRepository(shopRemoteDataSource: ShopRemoteDataSource, localDataSource: ShopLocalDataSource) : ShopRepository {
        return ShopRepositoryImpl(shopRemoteDataSource,localDataSource)
    }
}