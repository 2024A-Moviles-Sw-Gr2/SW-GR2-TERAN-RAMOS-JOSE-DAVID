package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Aplicar insets para soportar barras de estado y navegación
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias a los campos de texto y botón
        val usuarioEditText = findViewById<TextInputEditText>(R.id.textInputEditText)
        val contrasenaEditText = findViewById<TextInputEditText>(R.id.textInputEditText)
        val iniciarSesionButton = findViewById<Button>(R.id.btn_iniciar_sesion)

        // Manejar el evento de clic en el botón de Iniciar Sesión
        iniciarSesionButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()
            val contrasena = contrasenaEditText.text.toString()

            // Aquí puedes agregar la lógica de autenticación
            if (usuario == "usuario" && contrasena == "usuario") {
                // Si la autenticación es exitosa, pasa a la siguiente actividad
                val intent = Intent(this, Inicio::class.java)
                startActivity(intent)
            } else {
                // Mostrar mensaje de error si las credenciales no son correctas
                usuarioEditText.error = "Usuario o contraseña incorrectos"
            }
        }
    }
}
