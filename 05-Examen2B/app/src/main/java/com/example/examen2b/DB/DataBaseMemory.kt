package com.example.examen2b.DB

import com.example.examen2b.entities.Genre
import com.example.examen2b.entities.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DataBaseMemory {
    companion object {
        val genresArray = arrayListOf<Genre>()
        init {
            val moviesFantasia = arrayListOf<Movie>()
            moviesFantasia.add(Movie("El laberinto del fauno",118, stringToLocalDate("2006-12-28"),8.2,true))
            moviesFantasia.add(Movie("The Shape of Water",123, stringToLocalDate("2017-12-20"),7.3,true))
            genresArray.add(Genre("Fantasia",7.75,120,"Guillermo del Toro",moviesFantasia))

            val moviesComediaRomantica = arrayListOf<Movie>()
            moviesComediaRomantica.add(Movie("Love Actually",135, stringToLocalDate("2003-12-15"),7.6,false))
            genresArray.add(Genre("Comedia romantica",7.0,105,"Richard Curtis",moviesComediaRomantica))

            val moviesThriller = arrayListOf<Movie>()
            moviesThriller.add(Movie("Seven",127, stringToLocalDate("1995-09-22"),8.6,false))
            moviesThriller.add(Movie("Gone Girl",149, stringToLocalDate("2014-10-03"),8.1,false))
            genresArray.add(Genre("Thriller",7.0,115,"David Fincher",moviesThriller))

            val moviesDrama = arrayListOf<Movie>()
            moviesDrama.add(Movie("La lista de Schildler",195, stringToLocalDate("1993-12-15"),8.9,true))
            moviesDrama.add(Movie("Salvar al soldado Ryan",169, stringToLocalDate("1998-06-24"),8.6,true))
            genresArray.add(Genre("Drama histórico",8.5,135,"Steven Spielberg",moviesDrama))
        }
        private fun stringToLocalDate(stringDate: String): LocalDate {
            var formatter = DateTimeFormatter.ISO_LOCAL_DATE
            return LocalDate.parse(stringDate,formatter)
        }
    }
}