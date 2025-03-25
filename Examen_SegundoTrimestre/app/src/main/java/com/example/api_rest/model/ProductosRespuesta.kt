package com.example.api_rest.model

data class ProductosRespuesta(
    val page: Int,
    val per_page: Int,
    val results: List<Producto>,
    val total: Int,
    val total_pages: Int
)