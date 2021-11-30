package com.example.shopinglisttesting.data.repository.shopping_item

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import com.example.shopinglisttesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
class ShoppingItemDaoText {


    @get:Rule
    var hiltRule: HiltAndroidRule = HiltAndroidRule(this)


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var shoppingDatabase: ShoppingDatabase
    private lateinit var shoppingDao: ShoppingItemDao


    @Before
    fun setup() {
        hiltRule.inject()
        shoppingDao = shoppingDatabase.shoppingDao()
    }

    @After
    fun teardown() {
        shoppingDatabase.close()
    }

    @Test
    fun insertItemToShoppingList() = runBlockingTest {

        val newItem = ShoppingItem("item name", 10, 100, "image.jpeg", 1)
        shoppingDao.addNewItem(newItem)
        val shoppingListItems = shoppingDao.observeAllItems().getOrAwaitValue()
        assertThat(shoppingListItems).contains(newItem)
    }


    @Test
    fun deleteItemFromShoppingList() = runBlockingTest {
        val newItem = ShoppingItem("item Name", 10, 100, "image.jpg", id = 1)
        shoppingDao.addNewItem(newItem)
        shoppingDao.removeItem(newItem)
        val shoppingItems = shoppingDao.observeAllItems().getOrAwaitValue()
        assertThat(shoppingItems).doesNotContain(newItem)
    }

    @Test
    fun getSumOfShoppingItemsCost() = runBlockingTest {
        val newItem1 = ShoppingItem("item Name", 10, 100, "image.jpg", id = 1)
        val newItem2 = ShoppingItem("item Name", 20, 100, "image.jpg", id = 2)
        val newItem3 = ShoppingItem("item Name", 30, 100, "image.jpg", id = 3)

        shoppingDao.addNewItem(newItem1)
        shoppingDao.addNewItem(newItem2)
        shoppingDao.addNewItem(newItem3)

        val total = shoppingDao.observeTotal().getOrAwaitValue()
        assertThat(total).isEqualTo(10 * 100 + 20 * 100 + 30 * 100)

    }


    @Test
    fun observeAllItems() = runBlockingTest {
        val newItem1 = ShoppingItem("item Name", 10, 100, "image.jpg", id = 1)
        val newItem2 = ShoppingItem("item Name", 20, 100, "image.jpg", id = 2)
        val newItem3 = ShoppingItem("item Name", 30, 100, "image.jpg", id = 3)

        shoppingDao.addNewItem(newItem1)
        shoppingDao.addNewItem(newItem2)
        shoppingDao.addNewItem(newItem3)

        val shoppingListItems = shoppingDao.observeAllItems().getOrAwaitValue()
        assertThat(shoppingListItems.size).isEqualTo(3)

    }

}