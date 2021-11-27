package com.example.shopinglisttesting.ui.fragment_add_item

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FragmentAddItemViewModel @Inject constructor() : ViewModel() {


    companion object {
        const val TAG = "FragmentAddItem"
    }


    private val addItemEventChannel = Channel<AddItemEvents>()
    val addItemEventFlow = addItemEventChannel.receiveAsFlow()

    fun addNewItem() {
        viewModelScope.launch {
            when {
                itemName.value == null -> {
                    addItemEventChannel.send(AddItemEvents.ShowInvalidInputMessage(message = "Item name cant be empty"))
                }
                quantity.value == null -> {
                    addItemEventChannel.send(AddItemEvents.ShowInvalidInputMessage(message = "Quantity cant be empty"))

                }
                costPerItem.value == null -> {
                    addItemEventChannel.send(AddItemEvents.ShowInvalidInputMessage(message = "Cost per item cant be empty"))
                }
                itemImage.value == null -> {
                    addItemEventChannel.send(AddItemEvents.ShowInvalidInputMessage(message = "Please add an item image"))
                }
                else -> {
                    val newItem = ShoppingItem(itemName.value!!,
                        quantity.value!!,
                        costPerItem.value!!,
                        itemImage.value!!)
                    Log.i(TAG, newItem.toString())
                }
            }
        }

    }


    val itemName = MutableLiveData<String?>(null)

    val quantity = MutableLiveData<Int?>(null)

    val costPerItem = MutableLiveData<Int?>(null)

    val itemImage = MutableLiveData<String?>(null)

}

sealed class AddItemEvents {
    data class ShowInvalidInputMessage(val message: String) : AddItemEvents()
}