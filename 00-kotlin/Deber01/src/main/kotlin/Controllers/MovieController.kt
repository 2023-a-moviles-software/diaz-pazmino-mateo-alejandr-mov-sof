package Controllers

import DAO.MovieDAO
import Entities.Genre
import Entities.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

object MovieController {
    val input = Scanner(System.`in`)
    fun showMovie(movie: Movie){
        println("Titulo: ${movie.name}\nDuración: ${movie.runtime}\nFecha de Estreno: ${movie.release}\nCalificación" +
                " de la Audiencia: ${movie.audienceScore}\nPosee algún premio Oscar: ${if(movie.hasOscar == true) "Si" else "No"}\n")
    }

    fun showAllMovies(movies: ArrayList<Movie>){
        movies.forEachIndexed { index:Int, movie ->
            println("${index}.")
            showMovie(movie)
        }
    }

    fun createMovies():ArrayList<Movie>{
        val movies = ArrayList<Movie>()
        do {
            val movieData = inputMovieData()
            movies.add(Movie(movieData.get(0) as String,movieData.get(1) as Int,
                stringToLocalDate(movieData.get(2) as String) ,movieData.get(3) as Double,movieData.get(4) as Boolean))
            println("¿Deseas añadir otra película?(Si/No)")
            var option = input.nextLine()
        }while (option.uppercase().equals("SI"))
        return movies
    }

    fun createMovie():Movie{
        val movieData = inputMovieData()
        return Movie(movieData.get(0) as String,movieData.get(1) as Int,
            stringToLocalDate(movieData.get(2) as String) ,movieData.get(3) as Double,movieData.get(4) as Boolean)
    }

    fun inputMovieData():ArrayList<Any>{
        val movieData = ArrayList<Any>()
        print("\nIngresa el nombre de la película: ")
        movieData.add(input.nextLine())
        print("\nIngresa la duración: ")
        movieData.add(input.nextLine().toInt())
        print("\nIngresa la fecha de estreno(YYYY-MM-DD): ")
        movieData.add(input.nextLine())
        print("\nIngresa la calificación de la audiencia: ")
        movieData.add(input.nextLine().toDouble())
        print("\n¿La película ha ganado algún premio Oscar?(Si/No) : ")
        movieData.add(input.nextLine().uppercase().equals("SI"))
        return movieData
    }

    fun getAllMovies(genres:ArrayList<Genre>):ArrayList<Movie>{
        return MovieDAO.getAll(genres)
    }

    fun editMovie(genres:ArrayList<Genre>): ArrayList<Genre> {
        var movies = getAllMovies(genres)
        showAllMovies(movies)
        print("\nIngresa el número del película que desees editar: ")
        val index = input.nextLine().toInt()
        var movieToEdit = movies.get(index)
        movieToEdit = editMovieAttribute(movieToEdit)
        return MovieDAO.update(genres,movieToEdit.movieId,movieToEdit)
    }

    private fun editMovieAttribute(movie: Movie):Movie{
        println("1.Titulo\n2.Duración\n3.Fecha de estreno\n4.Calificación de la audiencia\n5.Tiene algún premio Oscar")
        print("\nIngresa el número del atributo que desees editar: ")
        val option = input.nextLine().toInt()
        print("\nIngresa el nuevo valor: ")
        when(option){
            1 -> movie.name = input.nextLine()
            2 -> movie.runtime = input.nextLine().toInt()
            3 -> movie.release = stringToLocalDate(input.nextLine())
            4 -> movie.audienceScore = input.nextLine().toDouble()
            5 -> movie.hasOscar = input.nextLine().uppercase().equals("SI")
            else -> println("Ingresa un número del 1 al 5")
        }
        return movie
    }

    fun deleteMovie(genres: ArrayList<Genre>): ArrayList<Genre> {
        var movies = getAllMovies(genres)
        showAllMovies(movies)
        print("\nIngresa el número de película que deseas eliminar: ")
        val option = input.nextLine().toInt()
        return MovieDAO.delete(genres,movies.get(option).movieId)
    }

    private fun stringToLocalDate(stringDate: String):LocalDate{
        var formatter = DateTimeFormatter.ISO_LOCAL_DATE
        return LocalDate.parse(stringDate,formatter)
    }
}