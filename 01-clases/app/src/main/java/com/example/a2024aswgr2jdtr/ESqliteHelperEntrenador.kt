package com.example.a2024aswgr2jdtr

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearEntrenador(
        nombre:String,
        descripcion:String
    ):Boolean{
        val baseDatosEscritura =writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombre", nombre)
        valoresGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura.insert(
            "ENTRENADOR",
            null,
            valoresGuardar
        )
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }

    //Clase jueves 11 Julio
    fun eliminarEntrenadorFormulario(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "ENTRENADOR",
            "id=?",
            parametrosConsultaDelete

        )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String, descripcion: String, id: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        //where...
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "ENTRENADOR",
            valoresAActualizar, //nombre = Adrian, descripcion = ...
            "id=?",
            parametrosConsultaActualizar// [1]
        )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt() == -1) false else true
    }

    //Consultar entrenador
    fun consultarEntrenadorPorID(id:Int):BEntrenador?{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()

        val arregloParametrosConsultaLectura= arrayOf(
            id.toString()
        )

        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )
        // Logica busqueda

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BEntrenador>()
        if(existeAlMenosUno){
            do{
                val entrenador = BEntrenador(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2)
                )
                arregloRespuesta.add(entrenador)
            }while(resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return arregloRespuesta[0]
    }


}
