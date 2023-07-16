package mateoapp.com.appmoviles01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteClosable
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class ESquliteHelperEntrenador (
    contexto: Context?, //This
): SQLiteOpenHelper(
    contexto,
    "moviles" ,//nombre bdd
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

    fun crearEntrenador(nombre:String, descripcion: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoGuardar = basedatosEscritura.insert("ENTRENADOR",null,valoresAGuardar)
        basedatosEscritura.close()
        return resultadoGuardar.toInt() !== -1
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
    
    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        //Where ID = ?
        val parametrosConsultaDelete = arrayListOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "ENTRENADOR", //Nombre tabla
        "id=?", //Consulta Where
        parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun actualizarEntrenadorFormulario(nombre:String,descripcion:String,id:Int):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre",nombre)
        valoresAActualizar.put("descripcion",descripcion)
        //Where ID = ?
        val parametrosConsultaActualizar = arrayListOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "ENTRENADOR", //Nombre tabla
        valoresAActualizar, //Valores
        "id=?", //Consulta Where
        parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return resultadoActualizacion.toInt() != -1
    }

    fun consultarEntrenadorPorId(id:Int):BEntrenador{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsultaLectura, //Parametros
        )
        //Logica Busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if(id != null){
                    //Llenar el arreglo con un nuevo BEntrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}

private fun SQLiteDatabase.update(
    s: String,
    valoresAActualizar: ContentValues,
    s1: String,
    parametrosConsultaActualizar: ArrayList<String>
): Int {
    TODO("Not yet implemented")
}

private fun SQLiteDatabase.delete(
    s: String,
    s1: String,
    parametrosConsultaDelete: ArrayList<String>
): Int {
    TODO("Not yet implemented")
}
