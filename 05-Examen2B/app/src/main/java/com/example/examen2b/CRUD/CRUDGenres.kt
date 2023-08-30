package com.example.examen2b.CRUD

import com.example.examen2b.DB.DataBaseMemory
import com.example.examen2b.entities.Genre
import com.example.examen2b.entities.Movie

object CRUDGenres {
    fun create(name:String,averangeRating: Double,averangeDuration: Int,featuredDirector: String){
        val newGenre = Genre()
        newGenre.name = name
        newGenre.averangeRating = averangeRating
        newGenre.averangeDuration = averangeDuration
        newGenre.featuredDirector = featuredDirector
        newGenre.movies = arrayListOf<Movie>()
        DataBaseMemory.genresArray.add(newGenre)
    }

    fun delete(index: Int){
        DataBaseMemory.genresArray.removeAt(index)
    }

    fun update(index: Int,name:String,averangeRating: Double,averangeDuration: Int,featuredDirector: String){
        DataBaseMemory.genresArray[index].name = name
        DataBaseMemory.genresArray[index].averangeRating = averangeRating
        DataBaseMemory.genresArray[index].averangeDuration = averangeDuration
        DataBaseMemory.genresArray[index].featuredDirector = featuredDirector
    }

    fun get(index: Int):Genre{
        return DataBaseMemory.genresArray[index]
    }
}