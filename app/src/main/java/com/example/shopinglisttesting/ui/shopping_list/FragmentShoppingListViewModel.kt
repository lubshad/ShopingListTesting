package com.example.shopinglisttesting.ui.shopping_list

import androidx.lifecycle.ViewModel
import com.example.shopinglisttesting.data.repository.shopping_item.ShoppingItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FragmentShoppingListViewModel @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao
): ViewModel() {
    val shoppingItems = shoppingItemDao.observeAllItems()
}