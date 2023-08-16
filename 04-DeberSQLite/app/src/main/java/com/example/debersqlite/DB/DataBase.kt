package com.example.debersqlite.DB

import com.example.debersqlite.CRUD.GenreDAO
import com.example.debersqlite.CRUD.MovieDAO

class DataBase {
    companion object {
        var tableGenre: GenreDAO? = null
        var tableMovie: MovieDAO? = null
    }
}