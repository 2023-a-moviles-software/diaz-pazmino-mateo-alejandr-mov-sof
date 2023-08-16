package com.example.debersqlite.Activies

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.example.debersqlite.CRUD.MovieDAO
import com.example.debersqlite.DB.DataBase
import com.example.debersqlite.R
import com.example.debersqlite.entities.Movie
import com.google.android.material.snackbar.Snackbar

class EditMoviesView : AppCompatActivity() {
    var datePickerDialog: DatePickerDialog? = null
    var dateButton: Button? = null
    var indexGenre = 0
    var indexMovie = 0
    var movieSelected = Movie()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movies_view)
        indexGenre = intent.getIntExtra("indexGenre",0)
        indexMovie = intent.getIntExtra("indexMovie",0)
        movieSelected = DataBase.tableMovie?.getById(indexMovie)!!
        initDatePicker()
        dateButton = findViewById<Button>(R.id.btn_date_picker_edit)
        fillFields()
        val updateButton = findViewById<Button>(R.id.btn_update_movie_edit)
        updateButton.setOnClickListener {
            if(checkFields()){
                updateMovie()
                clearFields()
                showSnackbar("Película Editada")
            }else{
                showSnackbar("No se ha podido editar la Película")
            }

        }
    }

    private fun initDatePicker() {
        val dateSetListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener {
                datePicker, year, month, dayOfMonth ->
            val date:String = makeDateString(dayOfMonth,month+1,year)
            dateButton?.text = date
        }

        val year = movieSelected.release?.year
        val month = movieSelected.release?.monthValue?.minus(1)
        val day = movieSelected.release?.dayOfMonth

        val style = AlertDialog.THEME_HOLO_LIGHT

        datePickerDialog = DatePickerDialog(this,style,dateSetListener,year!!,month!!,day!!)
    }

    private fun makeDateString(dayOfMonth: Int, month: Int, year: Int): String {
        return "${year}-${month.toString().padStart(2,'0')}-${dayOfMonth.toString().padStart(2,'0')}"
    }

    fun openDatePicker(view: View){
        datePickerDialog?.show()
    }

    fun updateMovie(){
        val titleText = findViewById<TextView>(R.id.input_name_movie_edit).text.toString()
        val runtimeText = findViewById<TextView>(R.id.input_runtime_movie_edit).text.toString().toInt()
        val dateText = dateButton?.text.toString()
        val scoreText = findViewById<TextView>(R.id.input_score_movie_edit).text.toString().toDouble()
        val hasOscarBool = getOscarField()
        DataBase.tableMovie?.update(indexMovie,titleText,runtimeText,dateText,scoreText,hasOscarBool)
    }

    fun getOscarField():Boolean{
        return findViewById<RadioButton>(R.id.rb_oscar_true_movie_edit).isChecked
    }

    fun fillFields(){
        findViewById<TextView>(R.id.input_name_movie_edit).text = movieSelected.name
        findViewById<TextView>(R.id.input_runtime_movie_edit).text = movieSelected.runtime.toString()
        dateButton?.text = movieSelected.release.toString()
        findViewById<TextView>(R.id.input_score_movie_edit).text = movieSelected.audienceScore.toString()
        if (movieSelected?.hasOscar!!){
            findViewById<RadioButton>(R.id.rb_oscar_true_movie_edit).isChecked = true
        }else{
            findViewById<RadioButton>(R.id.rb_oscar_false_movie_edit).isChecked = true
        }
    }

    fun clearFields(){
        findViewById<TextView>(R.id.input_name_movie_edit).text = ""
        findViewById<TextView>(R.id.input_runtime_movie_edit).text = ""
        dateButton?.text = "Fecha de Lanzamiento"
        findViewById<TextView>(R.id.input_score_movie_edit).text = ""
        findViewById<RadioButton>(R.id.rb_oscar_true_movie_edit).isChecked = false
        findViewById<RadioButton>(R.id.rb_oscar_false_movie_edit).isChecked = false
    }

    fun showSnackbar(text: String){
        Snackbar.make(findViewById(R.id.cl_movies_edit),text, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
    }

    fun checkFields():Boolean{
        return !(findViewById<TextView>(R.id.input_name_movie_edit).text.toString() == "" ||
                findViewById<TextView>(R.id.input_runtime_movie_edit).text.toString() == "" ||
                dateButton?.text.toString() == "Fecha de Lanzamiento" ||
                findViewById<TextView>(R.id.input_score_movie_edit).text.toString() == "" ||
                (!findViewById<RadioButton>(R.id.rb_oscar_true_movie_edit).isChecked
                        && !findViewById<RadioButton>(R.id.rb_oscar_false_movie_edit).isChecked))
    }
}