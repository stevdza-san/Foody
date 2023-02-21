package com.example.campus.models


import com.google.gson.annotations.SerializedName

data class ProductsList(
    @SerializedName("results")
    val results: List<Product>
)