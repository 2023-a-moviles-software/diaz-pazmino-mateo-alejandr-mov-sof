import java.util.Date

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

//Funciones
//void -> Unit
fun imprimirNombre(Nombre: String): Unit{
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