package com.example.shopinglisttesting.data.model.shopping_item

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shopping_item_table")
data class ShoppingItem(
    val itemName: String,
    val quantity: Int,
    val costPerItem: Int,
    val itemImage: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)