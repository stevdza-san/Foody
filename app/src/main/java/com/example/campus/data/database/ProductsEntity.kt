package com.example.campus.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.campus.models.ProductsList
import com.example.campus.util.Constants.Companion.PRODUCTS_TABLE

@Entity(tableName = PRODUCTS_TABLE)
class ProductsEntity (
        var productsList: ProductsList
    ) {
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0
    }