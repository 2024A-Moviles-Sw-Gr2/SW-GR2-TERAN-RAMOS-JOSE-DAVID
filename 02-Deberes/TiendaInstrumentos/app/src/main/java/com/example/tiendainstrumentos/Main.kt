package com.example.tiendainstrumentos

import Instrumento
import Tienda

fun main() {
    // INSTRUMENTOS
    val instrumento1 = Instrumento(1, "Guitarra", 500.0, "Cuerdas")
    val instrumento2 = Instrumento(2, "Piano", 1500.0, "Teclado")
    val instrumento3 = Instrumento(3, "Batería", 800.0, "Percusion")
    val instrumento4 = Instrumento(4, "Violín", 700.0, "Cuerdas")
    val instrumento5 = Instrumento(5, "Flauta", 300.0, "Vientos")

    Instrumento.agregarInstrumento(instrumento1)
    Instrumento.agregarInstrumento(instrumento2)
    Instrumento.agregarInstrumento(instrumento3)
    Instrumento.agregarInstrumento(instrumento4)
    Instrumento.agregarInstrumento(instrumento5)


    println("Instrumentos guardados:")
    val todosInstrumentos = Instrumento.mostrarInstrumentos()
    todosInstrumentos.forEach { println(it) }

    // TIENDAS
    val tienda1 = Tienda(1, "Tienda de Instrumentos A", "Calle Principal 123")
    val tienda2 = Tienda(2, "Tienda de Instrumentos B", "Avenida Central 456")

    Tienda.agregarTienda(tienda1)
    Tienda.agregarTienda(tienda2)

    println("\nTiendas guardadas con sus instrumentos:")
    val todasTiendas = Tienda.mostrarTiendas()
    todasTiendas.forEach { tienda ->
        println(tienda)
        println("Instrumentos en esta tienda:")
        tienda.getInstrumentos().forEach { println(" - $it") }
        println()
    }

    // Actualizar un instrumento
    println("\nActualizando un instrumento:")
    val instrumentoActualizado = Instrumento(1, "Guitarra Eléctrica", 550.0, "Cuerdas")
    Instrumento.actualizarInstrumento(instrumentoActualizado)
    println("Instrumento actualizado:")
    println(instrumentoActualizado)

    println("\nInstrumentos después de la actualización:")
    val instrumentosActualizados = Instrumento.mostrarInstrumentos()
    instrumentosActualizados.forEach { println(it) }

    // Actualizar una tienda
    println("\nActualizando una tienda:")
    val tiendaActualizada = Tienda(1, "Tienda de Instrumentos A Renovada", "Calle Nueva 456")
    Tienda.actualizarTienda(tiendaActualizada)
    println("Tienda actualizada:")
    println(tiendaActualizada)

    println("\nTiendas después de la actualización:")
    val tiendasActualizadas = Tienda.mostrarTiendas()
    tiendasActualizadas.forEach { tienda ->
        println(tienda)
        println("Instrumentos en esta tienda:")
        tienda.getInstrumentos().forEach { println(" - $it") }
        println()
    }

    // Eliminar un instrumento
    println("\nEliminando un instrumento:")
    Instrumento.eliminarInstrumento(3) // Eliminar el instrumento con id 3

    println("\nInstrumentos después de la eliminación:")
    val instrumentosDespuésDeEliminar = Instrumento.mostrarInstrumentos()
    instrumentosDespuésDeEliminar.forEach { println(it) }

    // Eliminar una tienda
    println("\nEliminando una tienda:")
    Tienda.eliminarTienda(2) // Eliminar la tienda con id 2

    println("\nTiendas después de la eliminación:")
    val tiendasDespuésDeEliminar = Tienda.mostrarTiendas()
    tiendasDespuésDeEliminar.forEach { tienda ->
        println(tienda)
        println("Instrumentos en esta tienda:")
        tienda.getInstrumentos().forEach { println(" - $it") }
        println()
    }
}
