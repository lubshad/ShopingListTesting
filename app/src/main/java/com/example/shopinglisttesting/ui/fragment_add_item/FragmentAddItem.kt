package com.example.shopinglisttesting.ui.fragment_add_item

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.databinding.FragmentAddNewItemBinding
import com.example.shopinglisttesting.ui.fragment_add_item.AddItemEvents.*
import com.example.shopinglisttesting.ui.shopping_list.ADDED_ITEM_NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


const val ITEM_IMAGE_URL = "item_image_url"


@AndroidEntryPoint
class FragmentAddItem : Fragment(R.layout.fragment_add_new_item) {

    private val viewModel by viewModels<FragmentAddItemViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentAddNewItemBinding.bind(view)

        binding.apply {
            imageViewItem.setOnClickListener {
                val action = FragmentAddItemDirections.actionFragmentAddItemToFragmentGallery()
                findNavController().navigate(action)
            }


            editTextItemName.addTextChangedListener {
                if (!it.isNullOrEmpty()) {

                    viewModel.itemName.value = it.toString()
                } else {
                    viewModel.itemName.value = null
                }
            }

            editTextQuantity.addTextChangedListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.quantity.value = it.toString().toInt()
                } else {
                    viewModel.quantity.value = null
                }
            }

            editTextCost.addTextChangedListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.costPerItem.value = it.toString().toInt()
                } else {
                    viewModel.costPerItem.value = null
                }
            }

            buttonAddItem.setOnClickListener {
                viewModel.addNewItem()
            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addItemEventFlow.collect {
                when (it) {
                    is ShowInvalidInputMessage -> {
                        showToast(it.message)
                    }
                    is NavigateBackWithItemName -> {
                        navigateBackWithItemName(it.itemName)
                    }
                }
            }

        }
        viewModel.itemImage.observe(viewLifecycleOwner) {
            if (it != null) {
                Glide.with(view)
                    .load(it).transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(binding.imageViewItem)
            }
        }

        setFragmentResultListener(ITEM_IMAGE_URL) { _, bundle ->
            val result = bundle.getString(ITEM_IMAGE_URL)
            viewModel.itemImage.value = result
        }
    }

    private fun navigateBackWithItemName(itemName: String) {
        val bundle = bundleOf(ADDED_ITEM_NAME to itemName)
        setFragmentResult(ADDED_ITEM_NAME, bundle)
        findNavController().navigateUp()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }




}