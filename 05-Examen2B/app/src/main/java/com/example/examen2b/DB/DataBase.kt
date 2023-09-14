package com.example.examen2b.DB

import com.example.examen2b.CRUD.GenreDAO
import com.example.examen2b.CRUD.MovieDAO

class DataBase {
    companion object {
        var tableGenre: GenreDAO? = null
        var tableMovie: MovieDAO? = null
    }
}