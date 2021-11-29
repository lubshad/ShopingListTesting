package com.example.shopinglisttesting.ui.fragment_add_item

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.shopinglisttesting.MainCoroutineRule
import com.example.shopinglisttesting.data.repository.shopping_item.FakeRepositoryImpl
import com.example.shopinglisttesting.getOrAwait
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FragmentAddItemViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var viewModel: FragmentAddItemViewModel


    @Before
    fun setup() {
        viewModel = FragmentAddItemViewModel(FakeRepositoryImpl())
    }


    @Test
    fun itemNameNullReturnsItemNameCannotBeEmpty() = runBlockingTest {
        viewModel.itemName.value = ""
        viewModel.quantity.value = 1
        viewModel.costPerItem.value = 2
        viewModel.itemImage.value = "image.jpeg"
        viewModel.addNewItem()
        val result = viewModel.addItemEventFlow.asLiveData().getOrAwait()
        assertThat(result).isEqualTo(AddItemEvents.ShowInvalidInputMessage(message = "Item name cant be empty"))
    }

    @Test
    fun quantityNullReturnQuantityCannotBeEmpty() = runBlockingTest {
        viewModel.itemName.value = "Banana"
        viewModel.quantity.value = null
        viewModel.costPerItem.value = 2
        viewModel.itemImage.value = "image.jpeg"
        viewModel.addNewItem()
        val result = viewModel.addItemEventFlow.asLiveData().getOrAwait()
        assertThat(result).isEqualTo(AddItemEvents.ShowInvalidInputMessage(message = "Quantity cant be empty"))
    }

    @Test
    fun costPerItemReturnCostPerItemCannotBeEmpty() = runBlockingTest {
        viewModel.itemName.value = "Banana"
        viewModel.quantity.value = 1
        viewModel.costPerItem.value = null
        viewModel.itemImage.value = "image.jpeg"
        viewModel.addNewItem()
        val result = viewModel.addItemEventFlow.asLiveData().getOrAwait()
        assertThat(result).isEqualTo(AddItemEvents.ShowInvalidInputMessage(message = "Cost per item cant be empty"))
    }

    @Test
    fun ItemImageNullReturnsItemImageCannotBeEmptyMessage() = runBlockingTest {
        viewModel.itemName.value = "Banana"
        viewModel.quantity.value = 1
        viewModel.costPerItem.value = 2
        viewModel.itemImage.value = ""
        viewModel.addNewItem()
        val result = viewModel.addItemEventFlow.asLiveData().getOrAwait()
        assertThat(result).isEqualTo(AddItemEvents.ShowInvalidInputMessage(message = "Please add an item image"))
    }

    @Test
    fun validInputReturnsNavigateBackWithItemName() = runBlockingTest {
        viewModel.itemName.value = "Banana"
        viewModel.quantity.value = 1
        viewModel.costPerItem.value = 2
        viewModel.itemImage.value = "image.jpg"
        viewModel.addNewItem()
        val result = viewModel.addItemEventFlow.asLiveData().getOrAwait()
        assertThat(result).isEqualTo(AddItemEvents.NavigateBackWithItemName("Banana"))
    }
}