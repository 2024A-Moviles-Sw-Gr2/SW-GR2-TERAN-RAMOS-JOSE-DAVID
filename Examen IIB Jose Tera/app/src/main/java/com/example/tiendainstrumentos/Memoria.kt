package com.example.tiendainstrumentos

import android.util.Log

class Memoria {
    companion object {
        val tiendas = arrayListOf<TiendaEntity>()
        val instrumentos = arrayListOf<InstrumentoEntity>()

        init {
            initInstrumentos()
            initTiendas()
        }

        fun initInstrumentos() {
            instrumentos.add(InstrumentoEntity(1, "Guitarra", 150.0, "Cuerda"))
            instrumentos.add(InstrumentoEntity(2, "Piano", 3000.0, "Tecla"))
            instrumentos.add(InstrumentoEntity(3, "Flauta", 50.0, "Viento"))
            instrumentos.add(InstrumentoEntity(4, "Batería", 800.0, "Percusión"))
            instrumentos.add(InstrumentoEntity(5, "Violín", 500.0, "Cuerda"))
            instrumentos.add(InstrumentoEntity(6, "Saxofón", 700.0, "Viento"))
            instrumentos.add(InstrumentoEntity(7, "Trompeta", 400.0, "Viento"))
            instrumentos.add(InstrumentoEntity(8, "Clarinete", 350.0, "Viento"))
            instrumentos.add(InstrumentoEntity(9, "Contrabajo", 1200.0, "Cuerda"))
            instrumentos.add(InstrumentoEntity(10, "Órgano", 2500.0, "Tecla"))
            Log.d("Memoria", "Instrumentos iniciales: $instrumentos")
        }

        fun initTiendas() {
            tiendas.add(TiendaEntity(1, "Instrumentos Musicales S.A.", "Av. Principal 123", mutableListOf(1, 2, 3)))
            tiendas.add(TiendaEntity(2, "La Casa del Músico", "Calle Secundaria 456", mutableListOf(4, 5, 6)))
            tiendas.add(TiendaEntity(3, "Música y Arte", "Av. Central 789", mutableListOf(7, 8, 9)))
            tiendas.add(TiendaEntity(4, "Todo Música", "Callejón del Sol 1011", mutableListOf(10)))
            Log.d("Memoria", "Tiendas iniciales: $tiendas")
        }

        fun agregarInstrumentoTienda(id: Int, instrumento: InstrumentoEntity) {
            tiendas.forEach { tienda ->
                if (tienda.id == id) {
                    tienda.instrumentos?.add(instrumento.id)
                }
            }
        }

        fun idNuevaTienda(): Int {
            return tiendas.lastOrNull()?.id?.plus(1) ?: 1
        }

        fun idNuevoInstrumento(): Int {
            return instrumentos.lastOrNull()?.id?.plus(1) ?: 1
        }

        fun actualizarInstrumento(instrumento: InstrumentoEntity) {
            val instrumentoIndex = instrumentos.indexOfFirst { it.id == instrumento.id }
            if (instrumentoIndex != -1) {
                instrumentos[instrumentoIndex] = instrumento
            }
        }

        fun eliminarInstrumentoTienda(idTienda: Int, idInstrumento: Int) {
            val tiendaIndex = tiendas.indexOfFirst { it.id == idTienda }
            val instrumentos = tiendas[tiendaIndex].instrumentos
            instrumentos?.let {
                val instrumentoIndex = it.indexOfFirst { it == idInstrumento }
                if (instrumentoIndex != -1) {
                    it.removeAt(instrumentoIndex)
                }
            }
        }
    }
}
