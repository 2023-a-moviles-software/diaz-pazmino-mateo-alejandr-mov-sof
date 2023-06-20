package Entities

import java.time.LocalDate

class Movie {
    var movieId: Int = ((Math.random()*999)+1).toInt()
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

    private constructor(){}
}
