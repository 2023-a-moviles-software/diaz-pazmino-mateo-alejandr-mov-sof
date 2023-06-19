package DAO

import Genre

object GenreDAO {
    fun create(genres:ArrayList<Genre>){
        var node = JSON.toJson(genres)
        JSON.saveJson(node)
    }
    fun delete(){}
    fun update(){}
    fun get():ArrayList<Genre>{
        var node = JSON.loadJson()
        return JSON.fromJson(node,Genre::class.java)
    }

}