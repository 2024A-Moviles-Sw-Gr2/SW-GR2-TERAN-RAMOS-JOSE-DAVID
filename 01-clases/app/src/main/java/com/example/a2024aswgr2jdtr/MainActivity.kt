package com.example.a2024aswgr2jdtr

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_ciclo_vida)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            irActividad(ACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(
            R.id.btn_ir_ListView)
        botonListView.setOnClickListener{
            irActividad(BListView::class.java)
        }

        //Agregar acciones a los botones
        //Boton Intent Implicito
        val botonIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonIntentImplicito.setOnClickListener {

            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackContenidoIntentImplicito.launch(intentConRespuesta)
        }

        //Boton intent explcito

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito)
                    botonIntentExplicito
                        .setOnClickListener {
                            val intentExplicito = Intent(
                                this,
                                CIntentExplicitoParametros::class.java
                            )
                            intentExplicito.putExtra("nombre", "Adrian")
                            intentExplicito.putExtra("apellido", "Eguez")
                            intentExplicito.putExtra("edad", 34)
                            //Mandamos una clase
                            intentExplicito.putExtra("entrenador", BEntrenador(10,"Adrian","Eguez"))
                            callbackContenidoIntentImplicito.launch(intentExplicito)

                        }

    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // INTENTS

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    //Intent Explicito


    val callbackcontenidointentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                    result ->
    if(result.resultCode == Activity.RESULT_OK) {
        if (result.data != null) {
            // logica negocio
            val data = result.data;
            mostrarSnackbar(
                "${data?.getStringExtra("nombreMofificado")}"

            )
        }
    }
    }


    val callbackContenidoIntentImplicito = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            if(result.data != null){
                //logica negocio
                if(result.data!!.data !=null){
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri, null, null, null, null, null)

                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex (
                        ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    mostrarSnackbar ("Telefono $telefono")
                }
            }
        }
    }


}