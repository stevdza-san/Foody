package com.example.campus.data.database
import com.example.campus.data.database.ProductsDao
import com.example.campus.data.database.ProductsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productsDao: ProductsDao
) {
    fun readDatabase(): Flow<List<ProductsEntity>> {
        return productsDao.readProducts()
    }

    suspend fun insertProducts(productsEntity: ProductsEntity) {
        productsDao.insertProducts(productsEntity)
    }

}








