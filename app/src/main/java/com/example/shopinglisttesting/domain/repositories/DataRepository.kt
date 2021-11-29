package com.example.shopinglisttesting.domain.repositories

import androidx.lifecycle.LiveData
import com.example.shopinglisttesting.data.model.pixabay.PixabayResponse
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import com.example.shopinglisttesting.utils.Resource
import javax.inject.Singleton



interface DataRepository {
    suspend fun addNewItemToShoppingList(item: ShoppingItem)

    suspend fun deleteItemFromShoppingList(item: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotal(): LiveData<Int>
}