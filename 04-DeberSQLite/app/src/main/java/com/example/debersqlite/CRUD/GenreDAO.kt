package com.example.debersqlite.CRUD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.debersqlite.entities.Genre

class GenreDAO(context: Context?) : SQLiteOpenHelper(context, "Movies", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCreateTableGenre =
            """
                CREATE TABLE GENRE(
                    id INTEGER PRIMARY KEY,
                    name VARCHAR(50),
                    averange_rating DOUBLE,
                    averange_duration INTEGER,
                    featured_director VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCreateTableGenre)
        val scriptSQLCreateTableMovie =
            """
                CREATE TABLE MOVIE(
                    id INTEGER PRIMARY KEY,
                    name VARCHAR(50),
                    runtime INTEGER,
                    release_date DATE,
                    audienceScore DOUBLE,
                    oscar BOOLEAN,
                    genre_id INTEGER,
                    FOREIGN KEY(genre_id) REFERENCES GENRE(id) ON DELETE CASCADE
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCreateTableMovie)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun create(
        name: String,
        averangeRating: Double,
        averangeDuration: Int,
        featuredDirector: String
    ):Boolean {
        val newGenre = Genre()
        newGenre.name = name
        newGenre.averangeRating = averangeRating
        newGenre.averangeDuration = averangeDuration
        newGenre.featuredDirector = featuredDirector

        val dbWrite = writableDatabase
        val valuesToSave = ContentValues()
        valuesToSave.put("id",newGenre.genreId)
        valuesToSave.put("name",newGenre.name)
        valuesToSave.put("averange_rating",newGenre.averangeRating)
        valuesToSave.put("averange_duration",newGenre.averangeDuration)
        valuesToSave.put("featured_director",newGenre.featuredDirector)
        val resultSave = dbWrite.insert("GENRE",null,valuesToSave)
        dbWrite.close()
        return resultSave.toInt() !== -1
    }

    fun delete(id: Int):Boolean {
        val dbWrite = writableDatabase
        val parameters = arrayOf(id.toString())
        val resultDelete = dbWrite.delete("GENRE","id = ?",parameters)
        dbWrite.close()
        return resultDelete.toInt() !== -1
    }

    fun update(
        id: Int,
        name: String,
        averangeRating: Double,
        averangeDuration: Int,
        featuredDirector: String
    ): Boolean {
        val genre = Genre()
        genre.genreId = id
        genre.name = name
        genre.averangeRating = averangeRating
        genre.averangeDuration = averangeDuration
        genre.featuredDirector = featuredDirector

        val dbWrite =  writableDatabase
        val valuesToSave = ContentValues()
        valuesToSave.put("id",genre.genreId)
        valuesToSave.put("name",genre.name)
        valuesToSave.put("averange_rating",genre.averangeRating)
        valuesToSave.put("averange_duration",genre.averangeDuration)
        valuesToSave.put("featured_director",genre.featuredDirector)
        val parameters = arrayOf(genre.genreId.toString())
        val resultUpdate = dbWrite.update("GENRE",valuesToSave,"id = ?",parameters)
        dbWrite.close()
        return resultUpdate.toInt() !== -1
    }

    fun getAll(): ArrayList<Genre>{
        val dbRead = readableDatabase
        val scriptRead = """
            SELECT * FROM GENRE
        """.trimIndent()
        val resultRead =  dbRead.rawQuery(scriptRead,null)

        val genreExists =  resultRead.moveToFirst()
        var genreFound: Genre?
        val genres = ArrayList<Genre>()
        if(genreExists){
            do {
                val id = resultRead.getInt(0)
                val name = resultRead.getString(1)
                val averangeRating = resultRead.getDouble(2)
                val averangeDuration = resultRead.getInt(3)
                val featuredDirector = resultRead.getString(4)
                genreFound = Genre(id,name,averangeRating,averangeDuration,featuredDirector)
                genres.add(genreFound)
            }while (resultRead.moveToNext())
        }
        resultRead.close()
        dbRead.close()
        return genres
    }

    fun getById(id: Int): Genre? {
       val dbRead = readableDatabase
        val scriptRead = """
            SELECT * FROM GENRE WHERE id = ?
        """.trimIndent()
        val parameters = arrayOf(id.toString())
        val resultRead = dbRead.rawQuery(scriptRead,parameters)

        val genreExists =  resultRead.moveToFirst()
        var genreFound: Genre?
        if(genreExists){
            do {
                val id = resultRead.getInt(0)
                val name = resultRead.getString(1)
                val averangeRating = resultRead.getDouble(2)
                val averangeDuration = resultRead.getInt(3)
                val featuredDirector = resultRead.getString(4)
                genreFound = Genre(id,name,averangeRating,averangeDuration,featuredDirector)
            }while (resultRead.moveToNext())
        }else{
            genreFound = null
        }
        resultRead.close()
        dbRead.close()
        return genreFound
    }


}