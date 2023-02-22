package com.example.campus.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [ProductsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ProductsTypeConverter::class)
abstract class ProductsDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductsDao

}