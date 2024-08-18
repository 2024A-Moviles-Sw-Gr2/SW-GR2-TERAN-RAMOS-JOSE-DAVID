package com.example.proyecto
import android.util.Log

class Memoria {
    companion object {
        val items = arrayListOf(
            Item("Juan Pérez", "30 Jun, 10:00 am", "2 de 4", 5),
            Item("Ana Gómez", "1 Jul, 12:00 pm", "3 de 4", 4),
            Item("Carlos Ruiz", "2 Jul, 3:00 pm", "1 de 4", 3),
            Item("María López", "3 Jul, 5:00 pm", "4 de 4", 5),
            Item("Jose Teran", "6 Jul, 8:00 pm", "3 de 4", 5),
            Item("Matias Villarreal", "11 Jul, 10:00 pm", "2 de 4", 2),
            Item("Kenny Pinchao", "17 Jul, 12:00 pm", "0 de 4", 4),
            Item("Dario Charro", "22 Jul, 11:00 am", "1 de 4", 1)
        )

        init {
            Log.d("Memoria", "Items iniciales: $items")
        }

        fun agregarItem(item: Item) {
            items.add(item)
            Log.d("Memoria", "Item agregado: $item")
        }

        fun eliminarItem(index: Int) {
            if (index in items.indices) {
                val item = items.removeAt(index)
                Log.d("Memoria", "Item eliminado: $item")
            } else {
                Log.d("Memoria", "Índice fuera de rango: $index")
            }
        }

        fun actualizarItem(index: Int, nuevoItem: Item) {
            if (index in items.indices) {
                items[index] = nuevoItem
                Log.d("Memoria", "Item actualizado: $nuevoItem")
            } else {
                Log.d("Memoria", "Índice fuera de rango: $index")
            }
        }
        fun getItems(): List<Item> {
            return items
        }


    }
}
