package com.example.tiendainstrumentos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_binstrumentos : AppCompatActivity() {

    private var instrumentos = arrayListOf<InstrumentoEntity>()
    private var idTienda = 1
    private var index = -1

    private val callbackFormularioInstrumento = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                val instrumentoModificado = it.getParcelableExtra<InstrumentoEntity>("instrumentoModificado")
                val instrumentoNuevo = it.getParcelableExtra<InstrumentoEntity>("instrumentoNuevo")

                instrumentoModificado?.let { instrumento ->
                    instrumentos[index] = instrumento
                    Memoria.actualizarInstrumento(instrumento)
                } ?: instrumentoNuevo?.let { nuevoInstrumento ->
                    instrumentos.add(nuevoInstrumento)
                    Memoria.instrumentos.add(nuevoInstrumento)
                    Memoria.agregarInstrumentoTienda(idTienda, nuevoInstrumento)
                }

                actualizarListaInstrumentos()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_binstrumentos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_instrumentos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tienda = intent.getParcelableExtra<TiendaEntity>("tienda")
        Log.d("InstrumentosActivity", "Tienda recibida: $tienda")

        tienda?.let {
            findViewById<TextView>(R.id.id_tienda_nom).text = it.nombre
            val instrumentosTienda = it.instrumentos ?: mutableListOf()
            instrumentos = Memoria.instrumentos.filter { instrumento -> instrumentosTienda.contains(instrumento.id) } as ArrayList<InstrumentoEntity>
            idTienda = it.id
        } ?: run {
            Log.d("InstrumentosActivity", "No se recibió ninguna tienda.")
        }

        actualizarListaInstrumentos()

        findViewById<Button>(R.id.id_btn_crear_instrumento).setOnClickListener {
            crearInstrumento()
        }

        findViewById<Button>(R.id.btn_back).setOnClickListener {
            startActivity(Intent(this, GGoogleMapsActivity::class.java))
        }


        registerForContextMenu(findViewById(R.id.list_instrumentos))
    }

    private fun actualizarListaInstrumentos() {
        val listView = findViewById<ListView>(R.id.list_instrumentos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            instrumentos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    private fun crearInstrumento() {
        val intentCrear = Intent(this, activity_deditar_instrumentos::class.java)
        callbackFormularioInstrumento.launch(intentCrear)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_instrumento, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        index = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.id_mi_editar_instrumento -> {
                val intentEditar = Intent(this, activity_deditar_instrumentos::class.java)
                intentEditar.putExtra("instrumento", instrumentos[index])
                callbackFormularioInstrumento.launch(intentEditar)
                true
            }
            R.id.id_mi_eliminar_instrumento -> {
                abrirDialogoEliminar(instrumentos[index])
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogoEliminar(instrumento: InstrumentoEntity) {
        AlertDialog.Builder(this)
            .setTitle("¿Desea eliminar el instrumento?")
            .setPositiveButton("Aceptar") { _, _ ->
                eliminarInstrumento(instrumento)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun eliminarInstrumento(instrumento: InstrumentoEntity) {
        instrumentos.remove(instrumento)
        Memoria.instrumentos.remove(instrumento)
        Memoria.tiendas.find { it.id == idTienda }?.instrumentos?.remove(instrumento.id)
        actualizarListaInstrumentos()
    }
}
