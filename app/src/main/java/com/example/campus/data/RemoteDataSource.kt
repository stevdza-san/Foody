package com.example.campus.data

import com.example.campus.data.network.ProductsApi
import com.example.campus.models.Product
import com.example.campus.models.ProductsList
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val ProductsApi: ProductsApi
) {

    suspend fun getProducts(queries: Map<String, String>): Response<ProductsList> {
        return ProductsApi.getProducts(queries)
    }

}