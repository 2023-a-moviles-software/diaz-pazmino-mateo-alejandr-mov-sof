import DAO.GenreDAO
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import Genre
import com.fasterxml.jackson.databind.DeserializationFeature

var objectMapper: ObjectMapper = getDefaultObjectMapper()
fun main(args: Array<String>) {
    /*
    val comedy = Genre("comedy",4.6,150,"Juan", mutableListOf())
    val movie1 = Movie("This is Spinal Tap",82,LocalDate.of(1984,9,8),
        0.92,false,comedy)
    comedy.movies.add(movie1)
    val genres = jsonMapper().writeValueAsString(comedy)
    print(genres)*/


    //createJsonData()

/*
    val movie1 = Movie("This is Spinal Tap",82, LocalDate.of(1984,9,8),
        0.92,false)
    val movies : ArrayList<Movie> = ArrayList()
    movies.add(movie1)
    val genre1 = Genre("comedy",4.6,150,"Juan", movies)
    val genre2 = Genre("action",4.6,150,"Juan", movies)
    var genres: ArrayList<Genre> = ArrayList()
    genres.add(genre1)
    genres.add(genre2)
    GenreDAO.create(genres)
*/
    /*
    var node: JsonNode = toJson(comedy)
    println(jsonToString(node))
    val output: Writer
    val file =  File.createTempFile("jsonPrueba",".json",File("D:\\Documentos\\EPN\\Aplicaciones_Moviles"))
    output = BufferedWriter(FileWriter(file))
    output.write(jsonToString(node))
    output.close()*/
/*
    //fun readFileDirectlyAsText(fileName: String): String= File(fileName).readText(Charsets.UTF_8)
    var jsonleido = File("D:\\Documentos\\EPN\\Aplicaciones_Moviles\\jsonPrueba14996115306133117007.json").readText()
    println(jsonleido)
    var node = parse(jsonleido)
    //println(node.get("movies"))

    var genero:Genre=fromJson(node,Genre::class.java)
    println(genero.name)
    println(genero.averangeRating)
    println(genero.movies?.get(0)?.release)*/

    //Get

    var generos = GenreDAO.get()
    println(generos.get(0).genreId)
    println(generos.get(1).genreId)

}

fun getDefaultObjectMapper():ObjectMapper{
    var defaultObjectMapper = ObjectMapper()
    defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
    defaultObjectMapper.registerModule(JavaTimeModule())
    return defaultObjectMapper
}

fun parse(src:String):JsonNode{
    return objectMapper.readTree(src)
}

fun <A>  fromJson(node:JsonNode, clazz:Class<A>): A {
    return objectMapper.treeToValue(node,clazz)
}

fun toJson(a: Any):JsonNode{
    return objectMapper.valueToTree(a)
}

fun jsonToString(node:JsonNode):String{
    var objectWriter = objectMapper.writer()
    objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT)
    return objectWriter.writeValueAsString(node)
}

fun createJsonData(){

    var json = JSONObject()
    val movie1 = Movie("This is Spinal Tap",82,LocalDate.of(1984,9,8),
        0.92,false)
    val movies : ArrayList<Movie> = ArrayList()
    movies.add(movie1)
    val comedy = Genre("comedy",4.6,150,"Juan", movies)

    json.put("name",comedy.name)
    json.put("averangeRating",comedy.averangeRating)
    json.put("averangeDuration",comedy.averangeDuration)
    json.put("featuredDirector",comedy.featuredDirector)
    json.put("movies",JSONArray().putAll(comedy.movies))

    val output: Writer
    val file =  File.createTempFile("jsonPrueba",".json",File("D:\\Documentos\\EPN\\Aplicaciones_Moviles"))
    output = BufferedWriter(FileWriter(file))
    output.write(json.toString())
    output.close()
}