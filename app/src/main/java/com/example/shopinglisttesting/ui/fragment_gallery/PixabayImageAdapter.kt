@file:Suppress("SpellCheckingInspection")

package com.example.shopinglisttesting.ui.fragment_gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.shopinglisttesting.data.model.pixabay.Hit
import com.example.shopinglisttesting.databinding.PixabayImageItemBinding
import com.example.shopinglisttesting.ui.fragment_gallery.PixabayImageAdapter.PixabayImageViewHolder


class PixabayImageAdapter(val onItemClick: OnClickListener) :
    PagingDataAdapter<Hit, PixabayImageViewHolder>(PixabayDiffCallBack) {


    inner class PixabayImageViewHolder(private val binding: PixabayImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imageViewItem.setOnClickListener {

                val position = bindingAdapterPosition
                val item = getItem(position)
                if (item != null) {
                    val imageUrl = item.webformatURL
                    onItemClick.onItemClicked(imageUrl)
                }
            }
        }


        fun bind(item: Hit) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.webformatURL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewItem)
                textViewAuthor.text = item.user
            }
        }

    }


    object PixabayDiffCallBack : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean = oldItem == newItem

    }

    override fun onBindViewHolder(holder: PixabayImageViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val item = getItem(position)
            if (item != null) {
                holder.bind(item)
            }
        }
    }

    interface OnClickListener {
        fun onItemClicked(imageUrl: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayImageViewHolder {
        val binding =
            PixabayImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PixabayImageViewHolder(binding)
    }

}