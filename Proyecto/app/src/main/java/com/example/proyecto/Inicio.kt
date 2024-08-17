package com.example.proyecto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.ItemAdapter

class Inicio : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var mainItemAdapter: ItemAdapter

    private val items = listOf(
        Item("Juan Pérez", "30 Jun, 10:00 am", "2 de 4", 5),
        Item("Ana Gómez", "1 Jul, 12:00 pm", "3 de 4", 4),
        Item("Carlos Ruiz", "2 Jul, 3:00 pm", "1 de 4", 3),
        Item("María López", "3 Jul, 5:00 pm", "4 de 4", 5),
        Item("Jose Teran", "6 Jul, 8:00 pm", "3 de 4", 5),
        Item("Matias Villarreal", "11 Jul, 10:00 pm", "2 de 4", 2),
        Item("Kenny Pinchao", "17 Jul, 12:00 pm", "0 de 4", 4),
        Item("Dario Charro", "22 Jul, 11:00 am", "1 de 4", 1)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        mainRecyclerView = findViewById(R.id.recyclerView)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainItemAdapter = ItemAdapter(items)
        mainRecyclerView.adapter = mainItemAdapter

    }
}