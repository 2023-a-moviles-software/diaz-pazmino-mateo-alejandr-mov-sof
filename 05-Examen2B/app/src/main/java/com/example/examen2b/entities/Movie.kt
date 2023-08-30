package com.example.examen2b.entities

import java.time.LocalDate

class Movie {
    val movieId: Int = ((Math.random()*999)+1).toInt()
    var name: String? = null
    var runtime: Int = 0
    var release: LocalDate? = null
    var audienceScore: Double = 0.0
    var hasOscar: Boolean? = null

    constructor(name: String, runtime: Int, release: LocalDate, audienceScore: Double, hasOscar: Boolean) {
        this.name = name
        this.runtime = runtime
        this.release = release
        this.audienceScore = audienceScore
        this.hasOscar = hasOscar
    }
    constructor(){}

    override fun toString(): String {
        return "${name}"
    }

    fun getInfo():String{
        return "Título: ${this.name}\nDuración: ${this.runtime}\nFecha de lanzamiento: ${this.release.toString()}\n" +
                "Calificación de la audiencia: ${this.audienceScore}\nHa ganado un premio Oscar: ${if (this.hasOscar!!) "Si" else "no"}"
    }
}