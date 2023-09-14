package com.example.examen2b.CRUD

import android.util.Log
import androidx.lifecycle.Lifecycling
import androidx.lifecycle.ViewModel
import com.example.examen2b.entities.Genre
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.E
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await

class GenreDAO : ViewModel() {

    var query: Query? = null
    val db = Firebase.firestore
    var genresArray = ArrayList<Genre>()

    fun create(
        name: String,
        averangeRating: Double,
        averangeDuration: Int,
        featuredDirector: String
    ) {
        val newGenre = Genre()
        newGenre.name = name
        newGenre.averangeRating = averangeRating
        newGenre.averangeDuration = averangeDuration
        newGenre.featuredDirector = featuredDirector

        //val db = Firebase.firestore
        val refGenre = db.collection("genres")
        refGenre.document(newGenre.genreId.toString()).set(newGenre)
    }

    fun delete(id: Int) {
        val db = Firebase.firestore
        val refGenre = db.collection("genres")
        refGenre.document(id.toString()).delete()
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

    fun update(
        id: Int,
        name: String,
        averangeRating: Double,
        averangeDuration: Int,
        featuredDirector: String
    ) {
        val genre = Genre()
        genre.genreId = id
        genre.name = name
        genre.averangeRating = averangeRating
        genre.averangeDuration = averangeDuration
        genre.featuredDirector = featuredDirector

        val updates = hashMapOf(
            "name" to genre.name,
            "averangeRating" to genre.averangeRating,
            "averangeDuration" to genre.averangeDuration,
            "featuredDirector" to genre.featuredDirector
        )
        val refGenre = db.collection("genres")
        refGenre.document(genre.genreId.toString()).update(updates as Map<String, Any>)
    }

    suspend fun getAll(): ArrayList<Genre> {
        println("amtes de Firestore")
        val refGenre = db.collection("genres")
        val genresFound = ArrayList<Genre>()
        return try {
            val snapshot = refGenre.get().await()
            for (document in snapshot) {
                val data = document.data
                val genre = Genre(
                    document.id.toInt(),
                    data.get("name") as String,
                    data.get("averangeRating") as Double,
                    (data.get("averangeDuration") as Long).toInt(),
                    data.get("featuredDirector") as String
                )
                genresFound.add(genre)
            }
            return genresFound
        }catch (_: FirebaseFirestoreException){
            return ArrayList()
        }
    }

    suspend fun getById(id: Int): Genre {
        val refGenre = db.collection("genres")
        var genreFound = Genre()
        return try{
            val document = refGenre.document(id.toString()).get().await()
                genreFound = Genre(
                    (document.data?.get("genreId") as Long).toInt(),
                    document.data?.get("name") as String,
                    document.data?.get("averangeRating") as Double,
                    (document.data?.get("averangeDuration") as Long).toInt(),
                    document.data?.get("featuredDirector") as String
                )
            return genreFound
        }catch (_: FirebaseFirestoreException){
            return Genre()
        }

    }

    fun convertToGenre(genre: QueryDocumentSnapshot): Genre {
        return Genre(
            (genre.data["genreId"] as Long).toInt(),
            genre.data["name"] as String,
            genre.data["averangeRating"] as Double,
            (genre.data["averangeDuration"] as Long).toInt(),
            genre.data["featuredDirector"] as String
        )
    }

}