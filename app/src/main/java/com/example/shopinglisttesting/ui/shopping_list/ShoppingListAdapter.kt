package com.example.shopinglisttesting.ui.shopping_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import com.example.shopinglisttesting.databinding.ShoppingListItemBinding

class ShoppingListAdapter :
    ListAdapter<ShoppingItem, ShoppingListAdapter.ShoppingListViewHolder>(DiffCallback) {


    inner class ShoppingListViewHolder(binding: ShoppingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    object DiffCallback : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }

    }

}