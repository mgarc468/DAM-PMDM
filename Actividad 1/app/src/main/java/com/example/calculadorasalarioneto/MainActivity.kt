package com.example.calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
        val etSalarioBruto = findViewById<EditText>(R.id.etSalarioBruto)
        val etNumeroPagas = findViewById<EditText>(R.id.etNumeroPagas)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val spGrupoProfesional = findViewById<Spinner>(R.id.spGrupoProfesional)
        val spGradoDiscapacidad = findViewById<Spinner>(R.id.spGradoDiscapacidad)
        val spEstadoCivil = findViewById<Spinner>(R.id.spEstadoCivil)
        val etNumeroHijos = findViewById<EditText>(R.id.etNumeroHijos)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        // Listas para los spinners
        val gruposProfesionales = arrayOf("Ingenieros y Licenciados", "Ayudantes no Titulados", "Oficiales Administrativos")
        val discapacidades = arrayOf("Sin discapacidad", "33%", "50%", "65%")
        val estadosCiviles = arrayOf("Soltero", "Casado", "Divorciado")

        spGrupoProfesional.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, gruposProfesionales)
        spGradoDiscapacidad.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, discapacidades)
        spEstadoCivil.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estadosCiviles)

        // Listener del botón de cálculo
        btnCalcular.setOnClickListener {
            val salarioBruto = etSalarioBruto.text.toString().toDoubleOrNull() ?: 0.0
            val numeroPagas = etNumeroPagas.text.toString().toIntOrNull() ?: 12
            val edad = etEdad.text.toString().toIntOrNull() ?: 0
            val grupo = spGrupoProfesional.selectedItem.toString()
            val discapacidad = spGradoDiscapacidad.selectedItem.toString()
            val estadoCivil = spEstadoCivil.selectedItem.toString()
            val numeroHijos = etNumeroHijos.text.toString().toIntOrNull() ?: 0

            // Llamamos a los métodos de cálculo
            val salarioNeto = calcularSalarioNeto(salarioBruto, numeroPagas, grupo, discapacidad, estadoCivil, numeroHijos)
            val retencionIRPF = calcularIRPF(salarioBruto, numeroHijos)
            val deducciones = calcularDeducciones(discapacidad, estadoCivil)

            // Pasar los datos a la siguiente actividad
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SALARIO_BRUTO", salarioBruto)
            intent.putExtra("SALARIO_NETO", salarioNeto)
            intent.putExtra("RETENCION_IRPF", retencionIRPF)
            intent.putExtra("DEDUCCIONES", deducciones)
            startActivity(intent)
        }

    }

    // Método para calcular el IRPF
    private fun calcularIRPF(salarioBruto: Double, numeroHijos: Int): Double {
            var porcentajeIRPF = 0.15 // Base IRPF 15%

            if (numeroHijos > 0) {
                porcentajeIRPF -= 0.02 * numeroHijos // Reducimos un 2% por cada hijo
            }

            return salarioBruto * porcentajeIRPF
    }

    // Método para calcular el salario neto
    private fun calcularSalarioNeto(salarioBruto: Double, numeroPagas: Int, grupo: String, discapacidad: String, estadoCivil: String, numeroHijos: Int): Double {
        val deduccionesFijas = 0.10 // 10% como deducción por defecto
        var salarioNeto = salarioBruto - (salarioBruto * deduccionesFijas)

        // Si tiene hijos, reducimos el porcentaje de retención
        if (numeroHijos > 0) {
            salarioNeto += 1000.0 * numeroHijos // Añadir una deducción por cada hijo
        }

        // Ajustar deducciones según grupo profesional
        if (grupo == "Grupo A") {
            salarioNeto -= 0.05 * salarioBruto // Descuento adicional del 5% para Grupo A
        }
        return salarioNeto
    }

    // Método para calcular deducciones basadas en el estado civil y grado de discapacidad
    private fun calcularDeducciones(discapacidad: String, estadoCivil: String): Double {
        var deducciones = 0.0

        // Deducciones por discapacidad
        if (discapacidad == "50%" || discapacidad == "65%") {
            deducciones += 200.0 // Añadir deducción para discapacitados
        }

        // Deducciones por estado civil
        if (estadoCivil == "Casado") {
            deducciones += 500.0 // Si está casado, tiene una deducción extra
        }

        return deducciones
    }


}










