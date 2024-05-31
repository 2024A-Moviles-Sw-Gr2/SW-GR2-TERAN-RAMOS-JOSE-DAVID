import java.io.File

class Instrumento(
    private var idInstrumento: Int,
    private var nombreInstrumento: String,
    private var precio: Double,
    private var categoria: String
) {
    init {
        this.idInstrumento
        this.nombreInstrumento
        this.precio
        this.categoria
    }

    fun getCategoria(): String{
        return categoria
    }

    override fun toString(): String {
        return "$idInstrumento, $nombreInstrumento, $precio, $categoria"
    }

    fun getIdInstrumento(): Int {
        return idInstrumento
    }

    companion object {
        private val pathTiendas = "/Users/josedavidteranramos/Desktop/SW-GR2-TERAN-RAMOS-JOSE-DAVID/02-Deber01-CRUD_Kotlin/Deber01/TiendaInstrumentos/instrumentos.txt"


        fun agregarInstrumento(instrumento: Instrumento) {
            val file = File(pathTiendas)
            file.appendText("${instrumento}\n")
        }


        fun mostrarInstrumentos(): List<Instrumento> {
            val file = File(pathTiendas)
            val instrumentos = ArrayList<Instrumento>()
            file.forEachLine {
                val partes = it.split(", ")
                val id = partes[0].toInt()
                val nombre = partes[1]
                val precio = partes[2].toDouble()
                val categoria = partes[3]
                val instrumento = Instrumento(id, nombre, precio, categoria)
                instrumentos.add(instrumento)
            }
            return instrumentos
        }


        fun actualizarInstrumento(instrumento: Instrumento) {
            val instrumentos = mostrarInstrumentos().toMutableList()
            val index = instrumentos.indexOfFirst { it.idInstrumento == instrumento.idInstrumento }
            if (index != -1) {
                instrumentos[index] = instrumento
                val file = File(pathTiendas)
                file.writeText("")
                instrumentos.forEach {
                    file.appendText("${it}\n")
                }
            }
        }


        fun eliminarInstrumento(id: Int) {
            val instrumentos = mostrarInstrumentos()
            val filteredInstrumentos = instrumentos.filterNot { it.idInstrumento == id }
            val file = File(pathTiendas)
            file.writeText("")
            filteredInstrumentos.forEach {
                file.appendText("${it}\n")
            }
        }
    }
}
