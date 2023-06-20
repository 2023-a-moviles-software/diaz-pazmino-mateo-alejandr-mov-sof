package DAO

import Entities.Genre

object GenreDAO {
    fun create(genres:ArrayList<Genre>){
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun delete(genreId:Int,genres:ArrayList<Genre>){
        val index = getIndexById(genreId, genres)
        genres.removeAt(index)
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun update(genreId:Int, genres:ArrayList<Genre>, genreUpdated: Genre){
        val index = getIndexById(genreId, genres)
        genres.set(index,genreUpdated)
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun get():ArrayList<Genre>{
        var node = JSON.loadJson()
        return JSON.fromJson(node, Genre::class.java)
    }

    fun getByID(genreId:Int,genres:ArrayList<Genre>): Genre {
        val index = getIndexById(genreId, genres)
        return genres[index]
    }

    private fun getIndexById(genreId:Int,genres:ArrayList<Genre>):Int{
        genres.forEachIndexed { index:Int, genre: Genre ->
            if (genre.genreId==genreId)
                return index
        }
        return -1
    }

}