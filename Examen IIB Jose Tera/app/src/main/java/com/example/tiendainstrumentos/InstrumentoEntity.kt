package com.example.tiendainstrumentos

import android.os.Parcel
import android.os.Parcelable

class InstrumentoEntity(
    var id: Int,
    var nombre: String,
    var precio: Double,
    var categoria: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeDouble(precio)
        parcel.writeString(categoria)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return nombre + ":  " + precio
    }

    companion object CREATOR : Parcelable.Creator<InstrumentoEntity> {
        override fun createFromParcel(parcel: Parcel): InstrumentoEntity {
            return InstrumentoEntity(parcel)
        }

        override fun newArray(size: Int): Array<InstrumentoEntity?> {
            return arrayOfNulls(size)
        }
    }
}