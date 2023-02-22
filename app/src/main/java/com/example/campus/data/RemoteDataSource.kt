package com.example.campus.data

import com.example.campus.data.network.ProductsApi
import com.example.campus.models.ProductsList
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val ProductsApi: ProductsApi
) {

    suspend fun getProductsUnderCategory(queries: Map<String, String>): Response<ProductsList> {
        return ProductsApi.getProductsUnderCategory(queries)
    }

}