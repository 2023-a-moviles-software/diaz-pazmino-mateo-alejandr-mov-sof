package com.example.debersqlite.Activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.debersqlite.CRUD.GenreDAO
import com.example.debersqlite.DB.DataBase
import com.example.debersqlite.R
import com.google.android.material.snackbar.Snackbar

class EditGenresView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_genres_view)
        val index: Int = intent.getIntExtra("index",0)
        fillTextsView(index)
        val updateButton = findViewById<Button>(R.id.btn_edit_genre)
        updateButton.setOnClickListener {
            if(checkFields()){
                updateGenre(index)
                clearFields()
                showSnackbar("Genero Editado")
            }else{
                showSnackbar("No se ha podido editar el genero ")
            }

        }
    }

    fun fillTextsView(id:Int){
        val genreSelected = DataBase.tableGenre?.getById(id)
        val nameText =  findViewById<TextView>(R.id.input_name_genre_edit)
        nameText.setText(genreSelected!!.name)
        val ratingText =  findViewById<TextView>(R.id.input_rating_genre_edit)
        ratingText.setText(genreSelected.averangeRating.toString())
        val durationText = findViewById<TextView>(R.id.input_duration_genre_edit)
        durationText.setText(genreSelected.averangeDuration.toString())
        val directorText = findViewById<TextView>(R.id.input_director_genre_edit)
        directorText.setText(genreSelected.featuredDirector)
    }

    fun updateGenre(id: Int){
        val nameText =  findViewById<TextView>(R.id.input_name_genre_edit).text.toString()
        val ratingText =  findViewById<TextView>(R.id.input_rating_genre_edit).text.toString().toDouble()
        val durationText = findViewById<TextView>(R.id.input_duration_genre_edit).text.toString().toInt()
        val directorText = findViewById<TextView>(R.id.input_director_genre_edit).text.toString()
        DataBase.tableGenre?.update(id,nameText,ratingText,durationText,directorText)
    }

    fun clearFields(){
        findViewById<TextView>(R.id.input_name_genre_edit).text = ""
        findViewById<TextView>(R.id.input_rating_genre_edit).text = ""
        findViewById<TextView>(R.id.input_duration_genre_edit).text = ""
        findViewById<TextView>(R.id.input_director_genre_edit).text = ""
    }

    fun showSnackbar(text: String){
        Snackbar.make(findViewById(R.id.cl_genres_edit),text, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
    }

    fun checkFields():Boolean{
        return !(findViewById<TextView>(R.id.input_name_genre_edit).text.toString() == "" ||
                findViewById<TextView>(R.id.input_rating_genre_edit).text.toString() == "" ||
                findViewById<TextView>(R.id.input_duration_genre_edit).text.toString() == "" ||
                findViewById<TextView>(R.id.input_director_genre_edit).text.toString() == "")
    }
}