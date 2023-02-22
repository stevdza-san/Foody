package com.example.campus.data.network

import com.example.campus.models.Product
import com.example.campus.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductsApi {

    @GET("/category_id")
    suspend fun getProducts(
        @QueryMap queries: Map<String, String>
    ): Response<ProductsList>

}