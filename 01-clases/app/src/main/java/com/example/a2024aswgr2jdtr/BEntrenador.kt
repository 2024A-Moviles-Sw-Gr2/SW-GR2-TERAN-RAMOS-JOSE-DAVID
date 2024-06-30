package com.example.a2024aswgr2jdtr

class BEntrenador (
    val id: Int,
    var nombre: String,
    var descripcion:String?
){
    override fun toString(): String {
        return "$nombre $descripcion"
    }
}