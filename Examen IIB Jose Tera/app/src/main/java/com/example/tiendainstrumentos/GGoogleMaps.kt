package com.example.tiendainstrumentos

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    private var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ggoogle_maps)

        val latitud = intent.getDoubleExtra("latitud", 0.0)
        val longitud = intent.getDoubleExtra("longitud", 0.0)
        val botonCarolina = findViewById<Button>(R.id.btn_ir_ubicacion)

        botonCarolina.setOnClickListener {
            // Lanza una corrutina para obtener la ubicación y mover la cámara
            CoroutineScope(Dispatchers.Main).launch {
                val ubicacion = LatLng(latitud, longitud)
                ubicacion?.let {
                    val zoom = 17f
                    moverCamaraConZoom(it, zoom)
                    anadirMarcador(ubicacion, "Circuito")
                } ?: run {
                    mostrarSnackbar("No se pudo encontrar la ubicación.")
                }
            }
        }

        // Configuración de insets para edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        solicitarPermisos()
        iniciarLogicaMapa()
    }

    private fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                permisoCoarse == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(nombrePermisoFine, nombrePermisoCoarse), 1
            )
        }
    }

    private fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa()
                mostrarMapa()
                escucharListeners()
            }
        }
    }

    private fun mostrarMapa() {
        val zoom = 17f
        val quicentro = LatLng(-0.1755181190138262, -78.47918808450619)
        moverCamaraConZoom(quicentro, zoom)
    }

    private fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
            val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
            val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
            val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
            val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                    permisoCoarse == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    private fun moverCamaraConZoom(latLang: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, zoom))
    }

    private fun anadirMarcador(latLang: LatLng, title: String): Marker? {
        return mapa.addMarker(MarkerOptions().position(latLang).title(title))
    }

    private fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    private fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            mostrarSnackbar("setOnPolygonClickListener ${it.tag}")
        }
        mapa.setOnPolylineClickListener {
            mostrarSnackbar("setOnPolylineClickListener ${it.tag}")
        }
        mapa.setOnMarkerClickListener {
            mostrarSnackbar("setOnMarkerClickListener ${it.tag}")
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            mostrarSnackbar("setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            mostrarSnackbar("setOnCameraMoveStartedListener")
        }
        mapa.setOnCameraIdleListener {
            mostrarSnackbar("setOnCameraIdleListener")
        }
    }
}
