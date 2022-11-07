package com.example.shoppingapp_04.presentation.di

import com.example.shoppingapp_04.data.api.ShopApiService
import com.example.shoppingapp_04.data.db.ShopDAO
import com.example.shoppingapp_04.data.repository.datasource.ShopLocalDataSource
import com.example.shoppingapp_04.data.repository.datasource.ShopRemoteDataSource
import com.example.shoppingapp_04.data.repository.datasourceImpl.ShopLocalDataSourceImpl
import com.example.shoppingapp_04.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(shopDAO: ShopDAO) : ShopLocalDataSource {
        return ShopLocalDataSourceImpl(shopDAO)
    }

    @Singleton
    @Provides
    fun provideShopRemoteDataSource(shopApiService: ShopApiService) : ShopRemoteDataSource {
        return ShopRemoteDataSourceImpl(shopApiService)
    }
}