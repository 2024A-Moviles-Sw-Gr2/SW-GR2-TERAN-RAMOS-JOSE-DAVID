package com.example.proyecto

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ReservarViaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservar_viaje)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textNombre = findViewById<TextView>(R.id.text_nombre)
        val textAsientos = findViewById<TextView>(R.id.text_asientos)
        val textTiempoViaje = findViewById<TextView>(R.id.text_tiempo_viaje)

        val textPrecio = findViewById<TextView>(R.id.text_precio)
        val textOrigen = findViewById<TextView>(R.id.text_origen)
        val textDestino = findViewById<TextView>(R.id.text_destino)
        val textMarcaVehiculo = findViewById<TextView>(R.id.text_marca_vechiculo)
        val btnReservar = findViewById<Button>(R.id.btn_reservar)

        val index = intent.getIntExtra("ITEM_INDEX", -1)

        if (index != -1) {
            val item = Memoria.items.getOrNull(index)
            item?.let {
                textNombre.text = it.nombreConductor
                textAsientos.text = it.asientosReservados
                textTiempoViaje.text = it.fechaViaje
                textPrecio.text = "$25"
                textOrigen.text = "Tulcan"
                textDestino.text = "Quito"
                textMarcaVehiculo.text = "Marca Vehíclo: KIA/RIO"
            }
        }

        btnReservar.setOnClickListener {
            if (index != -1) {
                val item = Memoria.items.getOrNull(index)
                item?.let {
                    val partes = it.asientosReservados.split(" de ")
                    val asientosActuales = partes[0].toInt()
                    val totalAsientos = partes[1].toInt()
                    if (asientosActuales < totalAsientos) {
                        val nuevosAsientosReservados = "${asientosActuales + 1} de $totalAsientos"
                        val itemActualizado = it.copy(asientosReservados = nuevosAsientosReservados)
                        Memoria.actualizarItem(index, itemActualizado) // Actualiza en Memoria
                        textAsientos.text = nuevosAsientosReservados
                        mostrarSnackbar("Se ha reservado el viaje con éxito")
                    } else {
                        mostrarSnackbar("No hay más asientos disponibles")
                    }
                }
            }
            Handler(mainLooper).postDelayed({
                finish()
            }, 2000) // 2000 ms = 2 segundos, ajusta según necesites
        }

    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}