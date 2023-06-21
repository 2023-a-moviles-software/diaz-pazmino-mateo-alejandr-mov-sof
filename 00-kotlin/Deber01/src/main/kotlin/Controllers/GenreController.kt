package Controllers

import DAO.GenreDAO
import DAO.MovieDAO
import Entities.Genre
import Entities.Movie
import java.util.*
import kotlin.collections.ArrayList

class GenreController {
    var genres:ArrayList<Genre> = GenreDAO.get()

    val input = Scanner(System.`in`)
    fun showAllGenres(){
        this.genres.forEachIndexed { index:Int, genre ->
            println("${index}.")
            showGenre(genre)
            genre.movies?.forEach {
                MovieController.showMovie(it)
            }
        }
    }

    fun showOnlyGenres(){
        this.genres.forEachIndexed { index:Int, genre ->
            println("${index}.")
            showGenre(genre)
        }
    }

    fun showMoviesByGenre(){
        showOnlyGenres()
        print("\nIngresa el número del genero del que desees ver sus películas: ")
        var option = input.nextLine().toInt()
        this.genres.get(option).movies?.let { MovieController.showAllMovies(it) }
    }

    fun showGenre(genre:Genre){
        println("Genero: ${genre.name} \nCalificación Promedio: ${genre.averangeRating}\nDuración Promedio: " +
                "${genre.averangeDuration}\nDirector Destacado: ${genre.featuredDirector}\nPeliculas del Genero:\n")
    }

    fun createGenre(){
        val genreData = inputGenreData()
        var newGenre = Genre(genreData.get(0) as String,genreData.get(1) as Double,genreData.get(2) as Int,genreData.get(3)
            as String,genreData.get(4) as ArrayList<Movie>)
        this.genres.add(newGenre)
        GenreDAO.create(this.genres)
    }

    fun inputGenreData():ArrayList<Any>{
        val genreData = ArrayList<Any>()
        print("\nIngresa el nombre del genero: ")
        genreData.add(input.nextLine())
        print("\nIngresa la calificación promedio: ")
        genreData.add(input.nextLine().toDouble())
        print("\nIngresa la duración promedio: ")
        genreData.add(input.nextLine().toInt())
        print("\nIngresa el director destacado: ")
        genreData.add(input.nextLine())
        print("\nIngresa una pelicula del genero: ")
        genreData.add(MovieController.createMovies())
        return genreData
    }

    fun addMovieToGenre(){
        showOnlyGenres()
        print("\nIngresa el número del genero del que desees agregar una película: ")
        var index = input.nextLine().toInt()
        val newMovie = MovieController.createMovie()
        val genderUpdated = MovieDAO.create(newMovie,this.genres.get(index))
        GenreDAO.update(index,this.genres,genderUpdated)
        updateGenres()
    }

    fun editGenre(){
        showOnlyGenres()
        print("\nIngresa el número del genero que desees editar: ")
        val index = input.nextLine().toInt()
        var genre = GenreDAO.getByIndex(index,this.genres)
        genre = editGenreAttribute(genre)
        GenreDAO.update(index,this.genres,genre)
        updateGenres()
    }

    private fun editGenreAttribute(genre: Genre):Genre{
        println("1.Nombre\n2.Calificación Promedio\n3.Duración Promedio\n4.Director Destacado")
        print("\nIngresa el número del atributo que desees editar: ")
        val option = input.nextLine().toInt()
        print("\nIngresa el nuevo valor: ")
        when(option){
            1 -> genre.name = input.nextLine()
            2 -> genre.averangeRating = input.nextLine().toDouble()
            3 -> genre.averangeDuration = input.nextLine().toInt()
            4 -> genre.featuredDirector = input.nextLine()
            else -> println("Ingresa un número del 1 al 4")
        }
        return genre
    }

    fun editMovies(){
        this.genres = MovieController.editMovie(this.genres)
        GenreDAO.create(this.genres)
        updateGenres()
    }

    fun deleteGenre(){
        showOnlyGenres()
        print("\nIngresa el número del genero que deseas eliminar: ")
        val index = input.nextLine().toInt()
        GenreDAO.delete(index,this.genres)
        updateGenres()
    }

    fun deleteMovie(){
        this.genres = MovieController.deleteMovie(this.genres)
        GenreDAO.create(this.genres)
        updateGenres()
    }


    private fun updateGenres(){
        this.genres = GenreDAO.get()
    }
}