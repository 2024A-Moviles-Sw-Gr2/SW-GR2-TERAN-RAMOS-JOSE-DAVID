package com.example.tiendainstrumentos

import android.os.Parcel
import android.os.Parcelable

class TiendaEntity(
    var id: Int,
    var nombre: String,
    var direccion: String,
    var instrumentos: MutableList<Int>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readArrayList(Int::class.java.classLoader) as MutableList<Int>
    ) {}

    override fun toString(): String {
        return nombre
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(direccion)
        parcel.writeList(instrumentos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TiendaEntity> {
        override fun createFromParcel(parcel: Parcel): TiendaEntity {
            return TiendaEntity(parcel)
        }

        override fun newArray(size: Int): Array<TiendaEntity?> {
            return arrayOfNulls(size)
        }
    }
}