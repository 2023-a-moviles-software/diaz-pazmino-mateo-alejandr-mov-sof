import com.fasterxml.jackson.module.kotlin.jsonMapper
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    /*
    val comedy = Genre("comedy",4.6,150,"Juan", mutableListOf())
    val movie1 = Movie("This is Spinal Tap",82,LocalDate.of(1984,9,8),
        0.92,false,comedy)
    comedy.movies.add(movie1)
    val genres = jsonMapper().writeValueAsString(comedy)
    print(genres)*/

    createJsonData()
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
    val file =  File.createTempFile("jsonPrueba",".json",File("D:\\Documentos\\EPN\\Aplicaciones Moviles")
    )
    output = BufferedWriter(FileWriter(file))
    output.write(json.toString())
    output.close()
}