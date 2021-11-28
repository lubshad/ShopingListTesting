package com.example.shopinglisttesting.ui.shopping_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.databinding.FragmentShopingListBinding
import dagger.hilt.android.AndroidEntryPoint


const val ADDED_ITEM_NAME = "added_item_name"

@AndroidEntryPoint
class FragmentShoppingList: Fragment(R.layout.fragment_shoping_list) {


    private val viewModel by viewModels<FragmentShoppingListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShopingListBinding.bind(view)

        val shoppingListAdapter  = ShoppingListAdapter()

        binding.apply {

            recyclerViewShoppingList.adapter = shoppingListAdapter
            recyclerViewShoppingList.setHasFixedSize(true)
            fabAddItem.setOnClickListener {
                val action = FragmentShoppingListDirections.actionFragmentShoppingListToFragmentAddItem()
                findNavController().navigate(action)
            }

        }



        viewModel.shoppingItems.observe(viewLifecycleOwner) {
            shoppingListAdapter.submitList(it)
        }

        viewModel.itemTotal.observe(viewLifecycleOwner) {
            binding.textViewTotal.text = "Total $it"
        }


        setFragmentResultListener(ADDED_ITEM_NAME) { _, bundle ->
            val itemName = bundle.getString(ADDED_ITEM_NAME)
            val message = "$itemName added to shopping list"
            showMessage(message)

        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}