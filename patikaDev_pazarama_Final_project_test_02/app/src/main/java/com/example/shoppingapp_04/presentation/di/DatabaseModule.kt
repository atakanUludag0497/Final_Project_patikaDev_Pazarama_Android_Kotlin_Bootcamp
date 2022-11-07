package com.example.shoppingapp_04.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.shoppingapp_04.data.db.Converters
import com.example.shoppingapp_04.data.db.ShopDAO
import com.example.shoppingapp_04.data.db.ShopDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesShopDatabase(app : Application, gson : Gson) : ShopDatabase {
        return Room.databaseBuilder(app,ShopDatabase::class.java,"shop_database")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesShopDao(database: ShopDatabase) : ShopDAO {
        return database.shopDao()
    }
}