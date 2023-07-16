package com.example.examen1b.entities

class Genre {
    val genreId: Int = ((Math.random()*999)+1).toInt()
    var name:String? = null
    var averangeRating: Double = 0.0
    var averangeDuration: Int = 0
    var featuredDirector: String? = null
    var movies: ArrayList<Movie>? = null
    constructor(name:String,averangeRating: Double,averangeDuration: Int,featuredDirector: String,movies: ArrayList<Movie>){
        this.name = name
        this.averangeRating = averangeRating
        this.averangeDuration = averangeDuration
        this.featuredDirector = featuredDirector
        this.movies = movies
    }

    constructor(){}

    override fun toString(): String {
        return "${name}"
    }

    fun getInfo():String{
        return "Nombre del genero: ${this.name}\nCalificación promedio: ${this.averangeRating}\n" +
                "Duración promedio: ${this.averangeDuration}\nDirector destacado: ${this.featuredDirector}"
    }
}