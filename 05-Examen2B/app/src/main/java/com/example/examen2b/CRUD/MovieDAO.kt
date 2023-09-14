package com.example.examen2b.CRUD

import com.example.examen2b.entities.Movie
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDAO{

    fun create(idGenre: Int,name: String, runtime: Int, release: String,
               audienceScore: Double, hasOscar: Boolean){
        val newMovie = Movie(name,runtime, stringToLocalDate(release),audienceScore, hasOscar)
        val movie = hashMapOf(
            "movieId" to newMovie.movieId,
            "name" to newMovie.name,
            "runtime" to newMovie.runtime,
            "release" to newMovie.release.toString(),
            "audienceScore" to newMovie.audienceScore,
            "hasOscar" to newMovie.hasOscar
        )
        val db = Firebase.firestore
        val refMovie = db.collection("genres").document(idGenre.toString())
            .collection("movies").document(newMovie.movieId.toString()).set(movie)
    }

    fun delete(idMovie: Int,idGenre: Int){
        val db = Firebase.firestore
        val refMovie = db.collection("genres")
        refMovie.document(idGenre.toString())
            .collection("movies").document(idMovie.toString()).delete()
            .addOnSuccessListener {  }
            .addOnFailureListener{ }
    }

    fun update(idMovie: Int,idGenre: Int,name: String, runtime: Int, release: String,
               audienceScore: Double, hasOscar: Boolean){
        val updatedMovie = Movie(idMovie,name,runtime, stringToLocalDate(release),audienceScore, hasOscar)
        val updates = hashMapOf(
            "name" to updatedMovie.name,
            "runtime" to updatedMovie.runtime,
            "release" to updatedMovie.release.toString(),
            "audienceScore" to updatedMovie.audienceScore,
            "hasOscar" to updatedMovie.hasOscar
        )

        val db = Firebase.firestore
        val refMovie = db.collection("genres")
        refMovie.document(idGenre.toString()).collection("movies")
            .document(idMovie.toString()).update(updates as Map<String, Any>)
    }

    suspend fun getAllByGenre(idGenre:Int): ArrayList<Movie>{
        val db = Firebase.firestore
        val refMovie = db.collection("genres")
        val movies = ArrayList<Movie>()
        return try{
            val snapshot = refMovie.document(idGenre.toString()).collection("movies").get().await()
            for (movie in snapshot){
                movies.add(convertToMovie(movie))
            }
            return movies
        }catch (_: FirebaseFirestoreException) {
            return ArrayList()
        }
    }

    suspend fun getById(idMovie: Int,idGenre: Int): Movie{
        var movieFound = Movie()
        val db = Firebase.firestore
        val refMovie = db.collection("genres").document(idGenre.toString()).collection("movies")
        return try {
            val document = refMovie.document(idMovie.toString()).get().await()
            movieFound = Movie(
                (document.data?.get("movieId") as Long).toInt(),
                document.data!!["name"] as String,
                (document.data!!["runtime"] as Long).toInt(),
                stringToLocalDate(document.data!!["release"] as String),
                document.data!!["audienceScore"] as Double,
                document.data!!["hasOscar"] as Boolean
            )
            return movieFound
        }catch (_: FirebaseFirestoreException){
            return Movie()
        }
    }

    private fun stringToLocalDate(stringDate: String): LocalDate {
        var formatter = DateTimeFormatter.ISO_LOCAL_DATE
        return LocalDate.parse(stringDate,formatter)
    }

    fun convertToMovie(movie: QueryDocumentSnapshot):Movie{
        return Movie(
            (movie.data["movieId"] as Long).toInt(),
            movie.data["name"] as String,
            (movie.data["runtime"] as Long).toInt(),
            stringToLocalDate(movie.data["release"] as String),
            movie.data["audienceScore"] as Double,
            movie.data["hasOscar"] as Boolean
        )
    }

}