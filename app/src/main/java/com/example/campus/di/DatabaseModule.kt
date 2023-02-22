package com.example.campus.di

import android.content.Context
import androidx.room.Room
import com.example.campus.data.database.ProductsDatabase
import com.example.campus.util.Constants.Companion.PRODUCTS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ProductsDatabase::class.java,
        PRODUCTS_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ProductsDatabase) = database.productsDao()

}