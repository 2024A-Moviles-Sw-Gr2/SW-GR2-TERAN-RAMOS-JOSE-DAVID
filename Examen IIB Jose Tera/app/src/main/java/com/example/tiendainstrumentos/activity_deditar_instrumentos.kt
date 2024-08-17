package com.example.tiendainstrumentos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_deditar_instrumentos : AppCompatActivity() {

    var id: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deditar_instrumentos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val instrumento = intent.getParcelableExtra<InstrumentoEntity>("instrumento")

        if (instrumento != null) {
            findViewById<EditText>(R.id.input_nombre_instrumento).setText(instrumento.nombre)
            findViewById<EditText>(R.id.input_categoria_instrumento).setText(instrumento.categoria)
            findViewById<EditText>(R.id.input_precio_instrumento).setText(instrumento.precio.toString())
            id = instrumento.id
        }

        val guardarBtn = findViewById<Button>(R.id.btn_save_instrumento)
        guardarBtn.setOnClickListener {
            if (instrumento != null) {
                responseEditar()
            } else {
                responseCrear()
            }
        }
    }

    private fun responseEditar() {
        val response = Intent()
        val nombre = findViewById<EditText>(R.id.input_nombre_instrumento).text.toString()
        val tipo = findViewById<EditText>(R.id.input_categoria_instrumento).text.toString()
        val precioStr = findViewById<EditText>(R.id.input_precio_instrumento).text.toString()

        if (nombre.isNotEmpty() && tipo.isNotEmpty() && precioStr.isNotEmpty()) {
            val precio = precioStr.toDoubleOrNull() ?: 0.0

            val instrumentoModificado = InstrumentoEntity(id, nombre,precio, tipo)
            Memoria.actualizarInstrumento(instrumentoModificado)

            response.putExtra("instrumentoModificado", instrumentoModificado)
            setResult(RESULT_OK, response)
            finish()
        } else {
            // Mostrar mensaje de error o validación
        }
    }

    private fun responseCrear() {
        val response = Intent()

        val nombre = findViewById<EditText>(R.id.input_nombre_instrumento).text.toString()
        val tipo = findViewById<EditText>(R.id.input_categoria_instrumento).text.toString()
        val precioStr = findViewById<EditText>(R.id.input_precio_instrumento).text.toString()

        if (nombre.isNotEmpty() && tipo.isNotEmpty() && precioStr.isNotEmpty()) {
            val precio = precioStr.toDoubleOrNull() ?: 0.0

            val instrumentoNuevo = InstrumentoEntity(Memoria.idNuevoInstrumento(), nombre, precio, tipo)

            response.putExtra("instrumentoNuevo", instrumentoNuevo)

            setResult(RESULT_OK, response)
            finish()
        } else {
            // Mostrar mensaje de error o validación
        }
    }
}
