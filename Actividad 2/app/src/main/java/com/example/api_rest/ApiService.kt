package com.example.api_rest

import com.example.api_rest.model.ProductosRespuesta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getAllProductos(@Url url : String): Response<ProductosRespuesta>
}