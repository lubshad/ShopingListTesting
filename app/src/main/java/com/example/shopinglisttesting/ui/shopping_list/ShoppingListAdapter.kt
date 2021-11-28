package com.example.shopinglisttesting.ui.shopping_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.shopinglisttesting.data.model.shopping_item.ShoppingItem
import com.example.shopinglisttesting.databinding.ShoppingListItemBinding

class ShoppingListAdapter :
    ListAdapter<ShoppingItem, ShoppingListAdapter.ShoppingListViewHolder>(DiffCallback) {


    inner class ShoppingListViewHolder(private val binding: ShoppingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.itemImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewShoppingItem)
                textViewCount.text = item.quantity.toString()+ "x"
                textViewTotal.text = "Rs" + (item.quantity*item.costPerItem).toString()
                textViewItemName.text = item.itemName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ShoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
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