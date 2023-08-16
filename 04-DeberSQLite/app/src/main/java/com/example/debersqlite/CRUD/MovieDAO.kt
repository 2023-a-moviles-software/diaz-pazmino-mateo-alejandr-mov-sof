package com.example.debersqlite.CRUD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.debersqlite.entities.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDAO(context: Context?):SQLiteOpenHelper(context,"Movies",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {}

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun create(idGenre: Int,name: String, runtime: Int, release: String,
               audienceScore: Double, hasOscar: Boolean):Boolean{
        val newMovie = Movie(name,runtime, stringToLocalDate(release),audienceScore, hasOscar)
        val dbWrite = writableDatabase
        val valuesToSave = ContentValues()
        valuesToSave.put("id",newMovie.movieId)
        valuesToSave.put("name",newMovie.name)
        valuesToSave.put("runtime",newMovie.runtime)
        valuesToSave.put("release_date",newMovie.release.toString())
        valuesToSave.put("audienceScore",newMovie.audienceScore)
        valuesToSave.put("oscar",newMovie.hasOscar)
        valuesToSave.put("genre_id",idGenre)
        val resultSave = dbWrite.insert("MOVIE",null,valuesToSave)
        dbWrite.close()
        return resultSave.toInt() !==-1
    }

    fun delete(idMovie: Int):Boolean{
        val dbWrite = writableDatabase
        val parameters = arrayOf(idMovie.toString())
        val resultDelete = dbWrite.delete("MOVIE","id = ?",parameters)
        dbWrite.close()
        return resultDelete.toInt() !==-1
    }

    fun update(idMovie: Int,name: String, runtime: Int, release: String,
               audienceScore: Double, hasOscar: Boolean):Boolean{
        val updatedMovie = Movie(idMovie,name,runtime, stringToLocalDate(release),audienceScore, hasOscar)
        val dbWrite = writableDatabase
        val valuesToUpdate = ContentValues()
        valuesToUpdate.put("id",updatedMovie.movieId)
        valuesToUpdate.put("name",updatedMovie.name)
        valuesToUpdate.put("runtime",updatedMovie.runtime)
        valuesToUpdate.put("release_date",updatedMovie.release.toString())
        valuesToUpdate.put("audienceScore",updatedMovie.audienceScore)
        valuesToUpdate.put("oscar",updatedMovie.hasOscar)
        val parameters = arrayOf(updatedMovie.movieId.toString())
        val resultUpdate = dbWrite.update("MOVIE",valuesToUpdate,"id = ?",parameters)
        dbWrite.close()
        return resultUpdate.toInt() !== -1

    }

    fun getAllByGenre(idGenre:Int): ArrayList<Movie>{
        val dbRead = readableDatabase
        val scriptRead = """
            SELECT * FROM MOVIE WHERE genre_id = ?
        """.trimIndent()
        val parameters = arrayOf(idGenre.toString())
        val resultRead = dbRead.rawQuery(scriptRead,parameters)
        val movieExists = resultRead.moveToFirst()
        var movieFound: Movie?
        val movies = ArrayList<Movie>()
        if (movieExists){
            do{
                movieFound = Movie(
                    resultRead.getInt(0),
                    resultRead.getString(1),
                    resultRead.getInt(2),
                    stringToLocalDate(resultRead.getString(3)),
                    resultRead.getDouble(4),
                    resultRead.getInt(5) == 1
                )
                movies.add(movieFound)
            }while (resultRead.moveToNext())
        }
        resultRead.close()
        dbRead.close()
        return movies
    }

    fun getById(id: Int): Movie? {
        val dbRead = readableDatabase
        val scriptRead = """
            SELECT * FROM MOVIE WHERE id = ?
            """.trimIndent()
        val parameters = arrayOf(id.toString())
        val resultRead = dbRead.rawQuery(scriptRead,parameters)

        val movieExists = resultRead.moveToFirst()
        var movieFound: Movie?
        if (movieExists){
                movieFound = Movie(
                    resultRead.getInt(0),
                    resultRead.getString(1),
                    resultRead.getInt(2),
                    stringToLocalDate(resultRead.getString(3)),
                    resultRead.getDouble(4),
                    resultRead.getInt(5) == 1
                )
        }else{
            movieFound = null
        }
        resultRead.close()
        dbRead.close()
        return movieFound
    }

    private fun stringToLocalDate(stringDate: String): LocalDate {
        var formatter = DateTimeFormatter.ISO_LOCAL_DATE
        return LocalDate.parse(stringDate,formatter)
    }


}