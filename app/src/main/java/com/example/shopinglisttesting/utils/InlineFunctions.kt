package com.example.shopinglisttesting.utils

import androidx.appcompat.widget.SearchView


inline fun SearchView.onQuerySubmit(crossinline listener: (query: String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            listener(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })
}