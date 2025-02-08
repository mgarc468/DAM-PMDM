package com.example.api_rest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_rest.databinding.ActivityMainBinding
import com.example.api_rest.model.Producto
import com.example.api_rest.model.ProductosRespuesta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter: ProductoAdapter
    private val productosList = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        getProductos("products")
    }

    private fun initRecyclerView() {
        adapter = ProductoAdapter((productosList))
        binding.rvSeries.layoutManager = LinearLayoutManager(this)
        binding.rvSeries.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getProductos(query : String){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ProductosRespuesta> = getRetrofit()
                .create(ApiService::class.java).getAllProductos(query)

            val productos :ProductosRespuesta? = call.body()
            if (productos != null) {
                for(miProducto: Producto in productos.results){
                    Log.v("Productos", miProducto.toString())
                }
            }
            runOnUiThread {
                if(call.isSuccessful){
                    val productosL = productos?.results?.toMutableList() ?: mutableListOf()
                    productosList.clear()
                    productosList.addAll(productosL)
                    adapter.notifyDataSetChanged()

                }else{
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}