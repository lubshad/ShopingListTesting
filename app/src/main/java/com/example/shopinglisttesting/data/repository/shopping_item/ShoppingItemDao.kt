package com.example.shopinglisttesting.data.repository.shopping_item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem


@Dao
interface ShoppingItemDao {

    @Insert
    suspend fun addNewItem(item:ShoppingItem)

    @Delete
    suspend fun removeItem(item:ShoppingItem)

    @Query("SELECT * FROM shopping_item_table")
    fun observeAllItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(quantity * costPerItem) FROM shopping_item_table")
    fun observeTotal(): LiveData<Int>
}