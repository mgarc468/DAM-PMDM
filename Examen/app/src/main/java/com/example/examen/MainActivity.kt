package com.example.examen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.loginButton)
            .setOnClickListener{
                val usuario = findViewById<EditText>(R.id.emailET)
                val password = findViewById<EditText>((R.id.passwordET))

                if(usuario.text.isNotEmpty() && password.text.isNotEmpty()){

                    //Navegar a la siguiente view
                    val intent = Intent(this, ResultLoginActivity::class.java)

                    //Valores que queremos pasar a la otra activity
                    intent.putExtra("user", usuario.text.toString())
                    intent.putExtra("password", password.text.toString())

                    //Iniciamos la otra activity
                    startActivity(intent)
                }
                else{
                    usuario.setHintTextColor(Color.RED)
                    usuario.setHint("Debe de rellenar esta campo")
                }

            }
    }
}