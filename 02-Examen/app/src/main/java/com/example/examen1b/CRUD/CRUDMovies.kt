package com.example.examen1b.CRUD

import com.example.examen1b.DB.DataBaseMemory
import com.example.examen1b.entities.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object CRUDMovies {
    fun create(indexGenre: Int,name: String, runtime: Int, release: String,
               audienceScore: Double, hasOscar: Boolean){
        val newMovie = Movie(name,runtime, stringToLocalDate(release),audienceScore, hasOscar)
        DataBaseMemory.genresArray[indexGenre].movies?.add(newMovie)
    }

    fun delete(indexGenre: Int,indexMovie: Int){
        DataBaseMemory.genresArray[indexGenre].movies?.removeAt(indexMovie)
    }

    fun update(indexGenre: Int,indexMovie: Int,name: String, runtime: Int, release: String,
               audienceScore: Double, hasOscar: Boolean){
        val updatedMovie = Movie(name,runtime, stringToLocalDate(release),audienceScore, hasOscar)
        DataBaseMemory.genresArray[indexGenre].movies?.set(indexMovie, updatedMovie)
    }

    fun get(indexGenre: Int,indexMovie: Int): Movie? {
        return DataBaseMemory.genresArray[indexGenre].movies?.get(indexMovie)
    }

    private fun stringToLocalDate(stringDate: String): LocalDate {
        var formatter = DateTimeFormatter.ISO_LOCAL_DATE
        return LocalDate.parse(stringDate,formatter)
    }
}