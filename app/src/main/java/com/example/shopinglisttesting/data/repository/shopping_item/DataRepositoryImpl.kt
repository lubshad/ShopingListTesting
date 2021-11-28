package com.example.shopinglisttesting.data.repository.shopping_item

import androidx.lifecycle.LiveData
import com.example.shopinglisttesting.data.model.pixabay.PixabayResponse
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import com.example.shopinglisttesting.domain.pixabay_api.PixabayApi
import com.example.shopinglisttesting.domain.repositories.DataRepository
import com.example.shopinglisttesting.utils.Resource
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
    private val pixabayApi: PixabayApi
): DataRepository {
    override suspend fun addNewItemToShoppingList(item: ShoppingItem) {
        shoppingItemDao.addNewItem(item)
    }

    override suspend fun deleteItemFromShoppingList(item: ShoppingItem) {
        shoppingItemDao.removeItem(item)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
       return shoppingItemDao.observeAllItems()
    }

    override fun observeTotal(): LiveData<Int> {
        return shoppingItemDao.observeTotal()
    }

    override suspend fun searchForImages(query: String): Resource<PixabayResponse> {
        return try {
            val data = pixabayApi.searchPhoto(query, page = 1, perPage = 10)
            Resource.Success(data)
        } catch (e:Exception) {
            Resource.Error(error = "Unexpected Error Occurred")
        }
    }
}