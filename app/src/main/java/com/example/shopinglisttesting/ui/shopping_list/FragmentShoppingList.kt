package com.example.shopinglisttesting.ui.shopping_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.databinding.FragmentShopingListBinding

class FragmentShoppingList: Fragment(R.layout.fragment_shoping_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShopingListBinding.bind(view)

        binding.apply {
            fabAddItem.setOnClickListener {
                val action = FragmentShoppingListDirections.actionFragmentShoppingListToFragmentAddItem()
                findNavController().navigate(action)
            }
        }
    }

}