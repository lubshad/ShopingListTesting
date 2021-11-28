package com.example.shopinglisttesting.ui.shopping_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.databinding.FragmentShopingListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentShoppingList: Fragment(R.layout.fragment_shoping_list) {


    private val viewModel by viewModels<FragmentShoppingListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShopingListBinding.bind(view)

        binding.apply {
            fabAddItem.setOnClickListener {
                val action = FragmentShoppingListDirections.actionFragmentShoppingListToFragmentAddItem()
                findNavController().navigate(action)
            }
        }


        viewModel.shoppingItems.observe(viewLifecycleOwner) {

        }
    }

}