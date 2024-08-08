package com.example.tiendainstrumentos

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var index = 1

    val callbackFormularioTienda = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val tiendaModificada = data.getParcelableExtra<TiendaEntity>("tiendaModificada")
                val tiendaNueva = data.getParcelableExtra<TiendaEntity>("tiendaNueva")

                tiendaModificada?.let {
                    Memoria.tiendas.removeAt(index)
                    Memoria.tiendas.add(index, it)
                } ?: tiendaNueva?.let {
                    Memoria.tiendas.add(it)
                }

                actualizarLista()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_tiendas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("TiendasActivity", "Tiendas iniciales: ${Memoria.tiendas}")

        // Configurar ListView
        actualizarLista()

        // Botón para crear nueva tienda
        val botonCrearTienda = findViewById<Button>(R.id.id_btn_crear_tienda)
        botonCrearTienda.setOnClickListener {
            crearTienda()
        }

        // Registrar el ListView para el menú contextual
        registerForContextMenu(findViewById(R.id.list_tienda))
    }

    private fun crearTienda() {
        val intentCrear = Intent(this, activity_beditar_tienda::class.java)
        callbackFormularioTienda.launch(intentCrear)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_tienda, menu)

        // Obtener la posición seleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        index = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.id_mi_editar_tienda -> {
                val intentEditar = Intent(this, activity_beditar_tienda::class.java)
                intentEditar.putExtra("tienda", Memoria.tiendas[index])
                callbackFormularioTienda.launch(intentEditar)
                true
            }
            R.id.id_mi_eliminar_tienda -> {
                abrirDialogoEliminar(index)
                true
            }
            R.id.id_mi_ver_instrumentos -> {
                val tiendaSeleccionada = Memoria.tiendas[index]
                Log.d("TiendasActivity", "Tienda seleccionada: $tiendaSeleccionada")
                Log.d("TiendasActivity", "Instrumentos en la tienda: ${tiendaSeleccionada.instrumentos}")

                val intent = Intent(this, activity_binstrumentos::class.java)
                intent.putExtra("tienda", tiendaSeleccionada)
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogoEliminar(index: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta tienda?")
        builder.setPositiveButton("Aceptar") { _, _ ->
            Memoria.tiendas.removeAt(index)
            actualizarLista()
        }
        builder.setNegativeButton("Cancelar", null)
        builder.create().show()
    }

    private fun actualizarLista() {
        val listView = findViewById<ListView>(R.id.list_tienda)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Memoria.tiendas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}