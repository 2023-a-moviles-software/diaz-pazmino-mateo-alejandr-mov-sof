import DAO.GenreDAO


fun main(args: Array<String>) {
    /*
    val comedy = Entities.Genre("comedy",4.6,150,"Juan", mutableListOf())
    val movie1 = Entities.Movie("This is Spinal Tap",82,LocalDate.of(1984,9,8),
        0.92,false,comedy)
    comedy.movies.add(movie1)
    val genres = jsonMapper().writeValueAsString(comedy)
    print(genres)*/


    //createJsonData()

/*
    val movie1 = Entities.Movie("This is Spinal Tap",82, LocalDate.of(1984,9,8),
        0.92,false)
    val movies : ArrayList<Entities.Movie> = ArrayList()
    movies.add(movie1)
    val genre1 = Entities.Genre("comedy",4.6,150,"Juan", movies)
    val genre2 = Entities.Genre("action",4.6,150,"Juan", movies)
    var genres: ArrayList<Entities.Genre> = ArrayList()
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

    var genero:Entities.Genre=fromJson(node,Entities.Genre::class.java)
    println(genero.name)
    println(genero.averangeRating)
    println(genero.movies?.get(0)?.release)*/

    //Get

    var generos = GenreDAO.get()
    println(generos.get(0).genreId)
    println(generos.get(1).genreId)

}
