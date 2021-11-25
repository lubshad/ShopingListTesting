package com.example.shopinglisttesting.data.model.pixabay


@Suppress("SpellCheckingInspection")
data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)