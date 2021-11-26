package com.example.shopinglisttesting.ui.fragment_add_item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.databinding.FragmentAddNewItemBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentAddItem : Fragment(R.layout.fragment_add_new_item) {

    val viewModel by viewModels<FragmentAddItemViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentAddNewItemBinding.bind(view)

        binding.apply {
            imageViewItem.setOnClickListener {
                val action = FragmentAddItemDirections.actionFragmentAddItemToFragmentGallery()
                findNavController().navigate(action)
            }

            editTextItemName.addTextChangedListener {
                value ->
                viewModel.itemName.value = value.toString()
            }

            editTextQuantity.addTextChangedListener {
                value ->
                if (value.text != null) {
                    viewModel.quantity.value = value.toString().toInt()
                }
            }

            editTextCost.addTextChangedListener {
                value ->
                if (value.text  != null ) {
                    viewModel.costPerItem.value = value.toString().toInt()
                }
            }

            buttonAddItem.setOnClickListener {
                viewModel.addNewItem()
            }

        }
    }

}