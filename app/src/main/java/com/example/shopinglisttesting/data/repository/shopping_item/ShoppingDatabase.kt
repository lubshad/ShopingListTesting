package com.example.shopinglisttesting.data.repository.shopping_item

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem


@Database(entities = [ShoppingItem::class], version = 1, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {
        abstract fun shoppingDao() : ShoppingItemDao
}