package com.example.shopinglisttesting.ui.fragment_add_item

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

            editTextItemName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrEmpty()) {

                        viewModel.itemName.value = s.toString()
                    } else {
                        viewModel.itemName.value = null
                    }
                }

            })

            editTextQuantity.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrEmpty()) {
                        viewModel.quantity.value = s.toString().toInt()
                    } else {
                        viewModel.quantity.value = null
                    }
                }

            })

            editTextCost.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrEmpty()) {
                        viewModel.costPerItem.value = s.toString().toInt()
                    } else {
                        viewModel.costPerItem.value = null
                    }
                }

            })

            buttonAddItem.setOnClickListener {
                viewModel.addNewItem()
            }

        }
    }

}