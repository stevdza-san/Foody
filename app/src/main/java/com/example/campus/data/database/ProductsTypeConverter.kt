package com.example.campus.data.database

import androidx.room.TypeConverter
import com.example.campus.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductsTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun productToString(product: Product): String {
        return gson.toJson(product)
    }

    @TypeConverter
    fun stringToProduct(data: String): Product {
        val listType = object : TypeToken<Product>() {}.type
        return gson.fromJson(data, listType)
    }

}
