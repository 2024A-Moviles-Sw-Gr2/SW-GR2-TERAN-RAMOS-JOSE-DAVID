package com.example.a2024aswgr2jdtr

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {

    val arreglo = BBaseDeDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_List_View)
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //layout xml a usar
            arreglo
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged() //Actualiza UI
        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view
        )
        botonAnadirListView.setOnClickListener{
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_bList_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu : ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener Id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
      return when (item.itemId){
          R.id.mi_editar ->{
              mostarSnackbar(
                  "Editar $posicionItemSeleccionado"
              )
              return true
          }
          R.id.mi_eliminar ->{
              mostarSnackbar(
                  "Eliminar $posicionItemSeleccionado"
              )
              abrirDialogo()
              return true
          }
          else -> super.onContextItemSelected(item)
        }
    }


    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>)
    {
        arreglo.add(
            BEntrenador(4,"Wendy", "d@d.com")
        )
        adaptador.notifyDataSetChanged()
    }

    fun mostarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_bList_view),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                dialog, which ->
                mostarSnackbar("Acepto $which")
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val opciones = resources.getStringArray(
            R.array.string_array_opciones
        )
        val seleccionPrevia = booleanArrayOf(
            true, //Lunes
            false, //Martes
            false, //Miercoles
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {
                dialog, which, isChecked ->
                mostarSnackbar("Item: $which")
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }


}