package com.example.shopinglisttesting.data.repository.shopping_item

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ShoppingItemDaoText {


    private lateinit var shoppingDatabase: ShoppingDatabase
    private lateinit var shoppingDao: ShoppingItemDao


    @Before
    fun setup() {
        shoppingDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            ShoppingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        shoppingDao = shoppingDatabase.shoppingDao()
    }

    @After
    fun teardown() {
        shoppingDatabase.close()
    }

    @Test
    fun insertItemToShoppingList() = runBlocking {

        val newItem = ShoppingItem("item name", 10, 100, "image.jpeg", 1)
        shoppingDao.addNewItem(newItem)
        val shoppingListItems = shoppingDao.observeAllItems().value


    }

}