package com.example.campus.data.network

import com.example.campus.models.Product
import com.example.campus.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductsApi {

    @GET("/recipes/complexSearch")
    suspend fun getProducts(
        @QueryMap queries: Map<String, String>
    ): Response<ProductsList>

}