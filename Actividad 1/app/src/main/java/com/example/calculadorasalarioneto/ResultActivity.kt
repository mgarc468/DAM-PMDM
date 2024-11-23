package com.example.calculadorasalarioneto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorasalarioneto.R.id.main

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.result_activity)

        val tvSalarioBruto = findViewById<TextView>(R.id.tvSalarioBruto)
        val tvSalarioNeto = findViewById<TextView>(R.id.tvSalarioNeto)
        val tvRetencionIRPF = findViewById<TextView>(R.id.tvRetencionIRPF)
        val tvDeducciones = findViewById<TextView>(R.id.tvDeducciones)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Recuperar los datos pasados
        val salarioBruto = intent.getDoubleExtra("SALARIO_BRUTO", 0.0)
        val salarioNeto = intent.getDoubleExtra("SALARIO_NETO", 0.0)
        val retencionIRPF = intent.getDoubleExtra("RETENCION_IRPF", 0.0)
        val deducciones = intent.getDoubleExtra("DEDUCCIONES", 0.0)

        // Mostrar los resultados en la interfaz
        tvSalarioBruto.text = "Salario Bruto: $salarioBruto"
        tvSalarioNeto.text = "Salario Neto: $salarioNeto"
        tvRetencionIRPF.text = "Retención IRPF: $retencionIRPF"
        tvDeducciones.text = "Deducciones: $deducciones"

        // Acción del botón Volver
        btnVolver.setOnClickListener {
            finish()  // Cerrar la actividad de resultados
        }
    }
}
