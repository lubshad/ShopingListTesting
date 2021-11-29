package com.example.shopinglisttesting.data.repository.shopping_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglisttesting.data.model.pixabay.Hit
import com.example.shopinglisttesting.data.model.pixabay.PixabayResponse
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import com.example.shopinglisttesting.domain.repositories.DataRepository
import com.example.shopinglisttesting.utils.Resource

class FakeRepositoryImpl : DataRepository {

    private val shoppingDatabase = mutableListOf<ShoppingItem>()

    private val shoppingListLiveData = MutableLiveData<List<ShoppingItem>>(shoppingDatabase)

    private val totalLiveData = MutableLiveData<Int>()


    private fun refreshData() {
        shoppingListLiveData.postValue(shoppingDatabase)
        totalLiveData.postValue(getTotal())
    }

    private fun getTotal(): Int {
        return shoppingDatabase.sumByDouble { shoppingItem ->
            shoppingItem.costPerItem.toDouble() * shoppingItem.quantity.toDouble()
        }.toInt()
    }


    override suspend fun addNewItemToShoppingList(item: ShoppingItem) {
        shoppingDatabase.add(item)
        refreshData()
    }

    override suspend fun deleteItemFromShoppingList(item: ShoppingItem) {
        shoppingDatabase.remove(item)
        refreshData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingListLiveData
    }

    override fun observeTotal(): LiveData<Int> {
        return totalLiveData
    }

}