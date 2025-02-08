package com.example.api_rest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.api_rest.databinding.ItemproductoBinding
import com.example.api_rest.model.Producto
import com.squareup.picasso.Picasso

class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemproductoBinding.bind(view)

    fun bind(producto: Producto){
        Picasso.get().load(producto.image).into(binding.ivSerie)
        binding.tvTitle.setText("Titulo: "+producto.name)
        binding.tvCreator.setText("Descripción: " + producto.description)
        binding.tvRating.setText("Precio: "+ producto.price.toString())
    }
}