package com.example.shopinglisttesting.data.model.shopping_item

data class ShoppingItem(
    val itemName: String,
    val quantity: Int,
    val costPerItem: Int,
    val itemImage: String,
    val id: Int = 0,
)