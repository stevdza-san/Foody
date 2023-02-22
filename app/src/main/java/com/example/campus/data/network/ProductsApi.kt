package com.example.campus.data.network

import com.example.campus.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductsApi {

    /**
     * parameter with key `category_id`
     */
    @GET("/products")
    suspend fun getProductsUnderCategory(
        @QueryMap queries: Map<String, String>
    ): Response<ProductsList>

}