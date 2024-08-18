package com.example.proyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.ItemAdapter

class Inicio : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var mainItemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        mainRecyclerView = findViewById(R.id.recyclerView)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)

        // Obtén la lista de items desde Memoria
        val items = Memoria.getItems()

        mainItemAdapter = ItemAdapter(items)
        mainRecyclerView.adapter = mainItemAdapter
    }


}
