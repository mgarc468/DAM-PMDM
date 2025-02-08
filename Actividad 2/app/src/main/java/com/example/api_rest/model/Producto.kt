package com.example.api_rest.model

data class Producto(
    val _id: String,
    val active: Boolean,
    val category: String,
    val description: String,
    val image: String,
    val name: String,
    val price: Double
)