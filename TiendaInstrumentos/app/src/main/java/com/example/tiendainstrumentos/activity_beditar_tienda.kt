package com.example.tiendainstrumentos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_beditar_tienda : AppCompatActivity() {

    var id: Int = 1
    var instrumentos: MutableList<Int>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beditar_tienda)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_instrumentos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var tienda = intent.getParcelableExtra<TiendaEntity>("tienda")

        if (tienda != null) {
            findViewById<EditText>(R.id.input_nombre_tienda).setText(tienda.nombre)
            findViewById<EditText>(R.id.input_direccion_tienda).setText(tienda.direccion)
            id = tienda.id
            instrumentos = tienda.instrumentos
        }

        val guardarBoton = findViewById<Button>(R.id.id_btn_guardar)
        guardarBoton.setOnClickListener {
            if (tienda != null) {
                responseEditar()
            } else {
                responseCrear()
            }
        }
    }

    private fun responseEditar() {
        val response = Intent()

        val nombre = findViewById<EditText>(R.id.input_nombre_tienda).text.toString()
        val direccion = findViewById<EditText>(R.id.input_direccion_tienda).text.toString()

        val tiendaModificada = TiendaEntity(id, nombre, direccion, instrumentos)

        response.putExtra("tiendaModificada", tiendaModificada)

        setResult(RESULT_OK, response)
        finish()
    }

    private fun responseCrear() {
        val response = Intent()

        val nombre = findViewById<EditText>(R.id.input_nombre_tienda).text.toString()
        val direccion = findViewById<EditText>(R.id.input_direccion_tienda).text.toString()

        val nuevaTienda = TiendaEntity(Memoria.idNuevaTienda(), nombre, direccion, instrumentos)

        response.putExtra("tiendaNueva", nuevaTienda)

        setResult(RESULT_OK, response)
        finish()
    }
}
