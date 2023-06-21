package DAO

import Entities.Genre

object GenreDAO {
    fun create(genres:ArrayList<Genre>){
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun delete(genreIndex:Int,genres:ArrayList<Genre>){
        genres.removeAt(genreIndex)
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun update(genreIndex:Int, genres:ArrayList<Genre>, genreUpdated: Genre){
        //val index = getIndexById(genre, genres)
        genres.set(genreIndex,genreUpdated)
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun get():ArrayList<Genre>{
        var node = JSON.loadJson()
        return JSON.fromJson(node, Genre::class.java)
    }

    fun getByIndex(genreIndex:Int,genres:ArrayList<Genre>): Genre {
        return genres[genreIndex]
    }

}