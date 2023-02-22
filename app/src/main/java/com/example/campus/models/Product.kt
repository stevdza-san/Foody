package com.example.campus.models

import com.google.gson.annotations.SerializedName

//TODO need Image model


data class Product(
    @SerializedName("category_id")
    var categoryId: String,
    @SerializedName("create_date")
    val createDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("_images")
    val images: List<String>,
    @SerializedName("is_sold")
    val isSold: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("seller_id")
    val sellerId: String,
    @SerializedName("title")
    val title: String


)