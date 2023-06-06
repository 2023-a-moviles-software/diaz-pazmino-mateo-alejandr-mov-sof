import java.util.ArrayList
import java.util.Date
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")
    //Variable inmutable
    val inmutable: String = "Adrian";
    //inmutable = "pepe";
    //Variable mutable
    var mutable: String = "Vicente";

    //val > var
    //Duck Typing
    var ejemploVariable = "Adrian Diaz"
    val edadEjemplo = 12

    //Variables primitivas
    val nombreProfesor: String = "Adian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //Clases Java
    val FechaNacimiento: Date = Date()

    //Condicionales
    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        "C" -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("Es dificil")
        }
    }
    //IF en una linea
    val esSoltero = (estadoCivilWhen == "S")
    val conqueteo = if (esSoltero) "Si" else "No"
    calcularSueldo(10.00)
    calcularSueldo(10.00,12.00,20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros nombrados
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //ARREGLOS
    //Tipos de Arreglos
    //Arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglos Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)

    //OPERADORES -> Sierven para arreglos estaticos y dinamicos

    //FOR EACH -> Unit
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico.forEach{
        valoActual: Int -> println("Valor actual: ${valoActual}")
    }

    arregloDinamico.forEach{ println(it) } //it significa el elemento iterado

    arregloEstatico.forEachIndexed{
        indice: Int, valorActual: Int ->
        println("Valor ${valorActual} Indice: ${indice}")
    }

    println(respuestaForEach)

    //MAP -> Muta el arreglo (Cambia el arreglo)
    //1) Enviemos el nuevo valor ded la iteracion
    //2) Nos evuelve es un Nuevo arreglo con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map {
        valoActual: Int -> return@map valoActual.toDouble() + 100.00
    }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //Filter -> Filtrar el arreglo
    //1) Devolver una expresion (TRUE o FALSE)
    //2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico.filter {
        valorActual: Int -> val mayoresACinco: Boolean = valorActual > 5 //Condicion
        return@filter mayoresACinco
    }
    val respuestaFilterosDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterosDos)

    //OR AND
    //OR -> ANY (Alguno Cumple)
    //AND -> ALL (Todos Cumplen)

    val respuestaAny: Boolean = arregloDinamico.any {
        valorActual: Int -> return@any (valorActual > 5)
    }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico.all {
        valorActual: Int -> return@all (valorActual > 5 )
    }
    println(respuestaAll) //false

    //REDUCE -> Valor Acumulado
    //Valor acumulado = 0 (Siempre 0 en el lenguaje Kotlin)
    //[1,2,3,4,5] -> Suma de todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico.reduce {
        acumulado: Int, valorActual: Int -> return@reduce (acumulado+valorActual) //Logica de negocio
    }
    println(respuestaReduce)
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){//Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(
    //Ejemplo:
    //uno: Int, (Parametro (sin modificador de acceso))
    //private var uno: Int, // Propiedad Publica Clase numeros.uno
    //var uno: Int, //Propiedad de la clase (por efecto es PUBLIC)
    //public var uno: Int,
    protected val numeroUno: Int,
    protected val numeroDOS: Int,
){
    //var cedula: string = "" (public por defecto)
    //private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDOS; //this es opcional
        numeroUno; numeroDOS; //sin el "this", es lo mismo
        println("Inicializador")
    }
}

class Suma( //Constructor primario
    unoParametro: Int, //Parametro
    dosParametro: Int, //Parametro
): Numeros(unoParametro,dosParametro){  //Extendiendo y mandando los paramtros (super)
    init {  //Bloque de codigo del constructor primario
        this.numeroUno
        this.numeroDOS
    }
    constructor(//Segundo constructor
        uno: Int?,
        dos: Int    //Parametros
    ):this(
        if(uno == null) 0 else uno,
        dos
    )
    constructor(    //Tercer constructor
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )
    constructor(    //Cuarto constructor
        uno: Int?,
        dos: Int?
    ):this(
      if(uno == null) 0 else uno,
      if(dos == null) 0 else dos,
    )

    public fun sumar(): Int{
        val total = numeroUno + numeroDOS
        agregarHistorial(total)
        return total
    }

    companion object{ //Atributos y metodos "Compartidos" Singletons o Static de esta clase
        //Todas las instancias de esta clase comparten estos atributos y metodos
        //dentro del companion Object
        val pi = 3.14

        fun elevarAlCuadrado(num: Int):Int{
            return num * num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}



//Funciones
//void -> Unit
fun imprimirNombre(nombre: String): Unit{
    print("Nombre: ${nombre}")  //Template String
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
): Double{
    //Int -> Int? (nulleable)
    //String -> String? (nulleable)
    //Date -> Date? (nulleable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}