package com.example.deber03

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Item
import com.example.proyecto.Memoria
import com.example.proyecto.R
import com.example.proyecto.ReservarViaje

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_viajes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        (items as MutableList).clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreConductor: TextView = itemView.findViewById(R.id.nombreConductor)
        private val fechaViaje: TextView = itemView.findViewById(R.id.fechaViaje)
        private val asientosReservados: TextView = itemView.findViewById(R.id.asientosReservados)

        private val estrellas = listOf(
            itemView.findViewById<ImageView>(R.id.iconStar),
            itemView.findViewById<ImageView>(R.id.iconStar2),
            itemView.findViewById<ImageView>(R.id.iconStar3),
            itemView.findViewById<ImageView>(R.id.iconStar4),
            itemView.findViewById<ImageView>(R.id.iconStar5)
        )

        fun bind(item: Item) {
            nombreConductor.text = item.nombreConductor
            fechaViaje.text = item.fechaViaje
            asientosReservados.text = item.asientosReservados

            // Configura las estrellas
            for (i in estrellas.indices) {
                estrellas[i].visibility = if (i < item.estrellas) View.VISIBLE else View.GONE
            }

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ReservarViaje::class.java).apply {
                    putExtra("ITEM_INDEX", adapterPosition)
                }
                context.startActivity(intent)
            }
        }
    }
}
