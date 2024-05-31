import java.util.*

// Unit es similar a void en Java

fun imprimirNombre(nombre:String){
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requrido
    tasa:Double = 12.00, //Opcional, defecto
    bonoEspecial: Double?=null// Es Nullable
):Double{
    if(bonoEspecial == null){
        return sueldo * (sueldo/100)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}
fun main() {


    println("hola mundo")
    //Inmutables (no se puede RE ASIGNAR "="
    val inmutable: String = "Adrian"

    //Mutables
    var mutable: String = "Jose"
    mutable = "Teran"
    println(mutable)

    //Duck typing
    var ejemploVariable = "Jose Teran"
    val edadEjemplo: Int = 4
    ejemploVariable.trim()

    //Variable primitivas
    val nombre: String = "Jose"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorDeEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()


    //clase 10 de mayo

    when (estadoCivil) {
        ('C') -> {
            println("Casado")
        }

        'S' -> {
            println("Soltero")
        }

        else -> {
            println("No sabemos")
        }
    }

    calcularSueldo(12.00)// El siempre requerido
    calcularSueldo(
        12.00,
        bonoEspecial = 40.0
    ) // Quiero manda parametro 1 y 3 (sueldo, tasa, bonoEspecial)
    calcularSueldo(bonoEspecial = 40.00, tasa = 12.0, sueldo = 30.0)


    val sumaUno = NumerosJava.Suma(3, 1);
    println(sumaUno.sumar())

    println(NumerosJava.Suma.pi)
    println(NumerosJava.Suma.elevarAlCuadrado(2))
    println(NumerosJava.Suma.historialSumas)

    //Arreglos 16/05/2024

    println("ARREGLOS")
    //Arreglas Estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println("Estatico" + arregloEstatico)


    //Arreglo Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(
        1,2,3,4,5,6,7,8,9
    )
    println("Dinamico" + arregloDinamico )

    //FOR EACH
    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }

    arregloDinamico.forEach{println("Valor Actual: ${it}")}


    //Map -> Modifica el arreglo
    // 1) enviamos el nuevo valor de la iteracion
    //2) Nos devuelve un NUEVO ARREGLO con valores de la iteracion
    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual: Int ->
        return@map valorActual.toDouble()+100
    }
    println(respuestaMap)

    val respuestaMapDos = arregloEstatico.map{it + 15}
    println(respuestaMapDos)


    // Operador Filter -> Filtra el arreglo
    // 1) Devolver una expresion (t o f)
    // Nuevo arreglo FILTRADO
    val respuestaFilter: List<Int> = arregloDinamico.
            filter { valorActural:Int ->
                val mayoresACinco: Boolean = valorActural > 5
                return@filter mayoresACinco
            }
    println("Filtrado :" + respuestaFilter)

    val respuestaFilterDos = arregloDinamico.filter{it <= 5}
    println("Filtrado funcional: " + respuestaFilterDos)


    //operadores lógicos
    // OR -> ANY
    // AND -> ALL
    val respuestaAny: Boolean = arregloDinamico.
            any{ valorActual: Int ->
                return@any (valorActual > 5)
            }
    println("Existe un valor mayor a 5? " + respuestaAny)

    val respuestaAll: Boolean = arregloDinamico.
            all{ valorActual: Int ->
                return@all (valorActual>5)
            }

    println("Son todos mayores a 5? " + respuestaAll)

    //Funcioón reduce (acumuladores)
    val respuestaReduce: Int = arregloDinamico.
            reduce{ acumulado: Int, valorActual: Int ->
                return@reduce (acumulado+valorActual)
            }

    println("Valor acumulado es " + respuestaReduce)


}

abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        print("Inicializado")
    }


    abstract class Numeros(
        protected val numeroUno: Int,
        protected val numeroDos: Int,
    ){
        init {
            this.numeroUno
            this.numeroDos
            println("Inicializando")
        }
    }

    class Suma(
        unoParametro: Int,
        dosParametro: Int
    ): Numeros(
        unoParametro,
        dosParametro
    ){
        public val soyPublicoExplicito: String = "Explicito"
        val soyPublicoImplicito:String= "Implicito"

        init {
            this.numeroUno
            this.numeroDos
            numeroUno //el  this es OPCIONAL
            numeroDos// el this es OPCIONAL
        }

        //Constructores secundarios  16/05/2024
        constructor(
            uno: Int?, // ? es que sea algun momento es nulleable
            dos: Int
        ):this(
            if(uno==null) 0 else uno,
            dos
        )

        constructor(
            uno: Int, // ? es que sea algun momento es nulleable
            dos: Int?
        ):this(
            if(dos==null) 0 else dos,
            uno
        )

        constructor(
            uno: Int?, // ? es que sea algun momento es nulleable
            dos: Int?
        ):this(
            if(uno==null) 0 else uno,
            if(dos==null) 0 else dos,
        )



        //devolvel la suma de dos numero
        //Companion object: comparte entre todas las instancias, compartir
        fun sumar():Int{
            val total = numeroUno + numeroDos
            agregarHistorial(total)
            return total
        }
        companion object {
            val pi =3.16
            fun elevarAlCuadrado(num:Int):Int{
                return num*num
            }
            val historialSumas = arrayListOf<Int>()
            fun agregarHistorial(valorTotalSuma:Int){
                historialSumas.add(valorTotalSuma)
            }
        }
    }






}