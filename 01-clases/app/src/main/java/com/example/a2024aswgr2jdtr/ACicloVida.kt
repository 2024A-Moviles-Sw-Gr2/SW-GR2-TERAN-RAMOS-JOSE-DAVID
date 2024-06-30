package com.example.a2024aswgr2jdtr

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {

    var textGlobal = ""
    fun mostarSnackbar(texto:String){
        textGlobal += texto
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aciclo_vida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_ciclo_vida)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mostarSnackbar("On create")
    }

    override fun onStart(){
        super.onStart()
        mostarSnackbar("On Start")
    }
    override fun onResume(){
        super.onResume()
        mostarSnackbar("On onResume")
    }
    override fun onRestart(){
        super.onRestart()
        mostarSnackbar("On Restart")
    }
    override fun onPause(){
        super.onPause()
        mostarSnackbar("On Pause")
    }
    override fun onStop(){
        super.onStop()
        mostarSnackbar("On Stop")
    }
    override fun onDestroy(){
        super.onDestroy()
        mostarSnackbar("On Destroy")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run{
            putString("variableTextGuardada", textGlobal)
        }
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Recuperar las varuables
        val textoRecuperadoDeVariable: String? = savedInstanceState.getString("variableTextGuardada")
        if(textoRecuperadoDeVariable != null){
            mostarSnackbar(textoRecuperadoDeVariable)
            textGlobal = textoRecuperadoDeVariable
        }
    }


}