import java.io.File

class Tienda(
    private var idTienda: Int,
    private var nombreTienda: String,
    private var direccion: String,
    private var instrumentos: ArrayList<Instrumento> = arrayListOf()
) {
    init {
        this.idTienda
        this.nombreTienda
        this.direccion
        this.instrumentos
    }

    override fun toString(): String {
        return "$idTienda, $nombreTienda, $direccion"
    }

    fun addInstrumento(instrumento: Instrumento) {
        instrumentos.add(instrumento)
    }

    fun getInstrumentos(): ArrayList<Instrumento>{
        return instrumentos
    }

    companion object {
        private val pathTiendas = "/Users/josedavidteranramos/Desktop/SW-GR2-TERAN-RAMOS-JOSE-DAVID/02-Deber01-CRUD_Kotlin/Deber01/TiendaInstrumentos/tiendas.txt"

        fun agregarTienda(tienda: Tienda) {
            val file = File(pathTiendas)
            file.appendText("${tienda}\n")
        }

        fun mostrarTiendas(): List<Tienda> {
            val file = File(pathTiendas)
            val tiendas = ArrayList<Tienda>()
            file.forEachLine {
                val partes = it.split(", ")
                val id = partes[0].toInt()
                val nombre = partes[1]
                val direccion = partes[2]
                val tienda = Tienda(id, nombre, direccion)
                tiendas.add(tienda)
            }

            val todosInstrumentos = Instrumento.mostrarInstrumentos()
            tiendas.forEach { tienda ->
                val instrumentosDeTienda = if (tienda.idTienda == 1) {
                    todosInstrumentos.filter { it.getCategoria() == "Cuerdas" }
                } else {
                    todosInstrumentos.filter { it.getCategoria() != "Cuerdas" }
                }
                tienda.instrumentos.addAll(instrumentosDeTienda)
            }
            return tiendas
        }

        fun actualizarTienda(tienda: Tienda) {
            val tiendas = mostrarTiendas().toMutableList()
            val index = tiendas.indexOfFirst { it.idTienda == tienda.idTienda }
            if (index != -1) {
                tiendas[index] = tienda
                val file = File(pathTiendas)
                file.writeText("")
                tiendas.forEach {
                    file.appendText("${it}\n")
                }
            }
        }

        fun eliminarTienda(id: Int) {
            val tiendas = mostrarTiendas()
            val filteredTiendas = tiendas.filterNot { it.idTienda == id }
            val file = File(pathTiendas)
            file.writeText("")
            filteredTiendas.forEach {
                file.appendText("${it}\n")
            }
        }
    }
}
