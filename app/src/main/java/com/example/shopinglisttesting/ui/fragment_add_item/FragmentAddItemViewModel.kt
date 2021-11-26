package com.example.shopinglisttesting.ui.fragment_add_item

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FragmentAddItemViewModel @Inject constructor(): ViewModel() {


    companion object {
        const val TAG = "FragmentAddItem"
    }

    fun addNewItem() {

        Log.i(TAG, itemName.value!!)
        Log.i(TAG, quantity.value!!.toString())
        Log.i(TAG, costPerItem.value!!.toString())
    }


    val itemName = MutableLiveData<String?>(null)

    val quantity = MutableLiveData<Int?>(null)

    val costPerItem = MutableLiveData<Int?>(null)

}