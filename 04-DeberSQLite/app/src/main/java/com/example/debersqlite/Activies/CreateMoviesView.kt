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
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class CreateMoviesView : AppCompatActivity() {
    var datePickerDialog: DatePickerDialog? = null
    var dateButton: Button? = null
    var indexGenre = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_movies_view)
        indexGenre = intent.getIntExtra("indexGenre",0)
        initDatePicker()
        dateButton = findViewById<Button>(R.id.btn_date_picker_new)
        val createMovieButton = findViewById<Button>(R.id.btn_create_movie_new)
        createMovieButton.setOnClickListener {
            if(checkFields()){
                createMovie()
                clearFields()
                showSnackbar("Película Creada")
            }else{
                showSnackbar("No se ha podido crear la Película")
            }

        }
    }


    private fun initDatePicker() {
        val dateSetListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener {
                datePicker, year, month, dayOfMonth ->
            //month += 1
            val date:String = makeDateString(dayOfMonth,month+1,year)
            dateButton?.text = date
        }
        val cal:Calendar = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val style = AlertDialog.THEME_HOLO_LIGHT

        datePickerDialog = DatePickerDialog(this,style,dateSetListener,year,month,day)
    }

    private fun makeDateString(dayOfMonth: Int, month: Int, year: Int): String {
        return "${year}-${month.toString().padStart(2,'0')}-${dayOfMonth.toString().padStart(2,'0')}"
    }

    fun openDatePicker(view: View){
        datePickerDialog?.show()
    }

    fun createMovie(){
        val titleText = findViewById<TextView>(R.id.input_name_movie_new).text.toString()
        val runtimeText = findViewById<TextView>(R.id.input_runtime_movie_new).text.toString().toInt()
        val dateText = dateButton?.text.toString()
        val scoreText = findViewById<TextView>(R.id.input_score_movie_new).text.toString().toDouble()
        val hasOscarBool = getOscarField()
        DataBase.tableMovie?.create(indexGenre,titleText,runtimeText,dateText,scoreText,hasOscarBool)
    }

    fun getOscarField():Boolean{
        return findViewById<RadioButton>(R.id.rb_oscar_true_movie_new).isChecked
    }


    fun clearFields(){
        findViewById<TextView>(R.id.input_name_movie_new).text = ""
        findViewById<TextView>(R.id.input_runtime_movie_new).text = ""
        dateButton?.text = "Fecha de Lanzamiento"
        findViewById<TextView>(R.id.input_score_movie_new).text = ""
        findViewById<RadioButton>(R.id.rb_oscar_true_movie_new).isChecked = false
        findViewById<RadioButton>(R.id.rb_oscar_false_movie_new).isChecked = false
    }

    fun showSnackbar(text: String){
        Snackbar.make(findViewById(R.id.cl_movies_new),text, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()

    }

    fun checkFields():Boolean{
        return !(findViewById<TextView>(R.id.input_name_movie_new).text.toString() == "" ||
                findViewById<TextView>(R.id.input_runtime_movie_new).text.toString() == "" ||
                dateButton?.text.toString() == "Fecha de Lanzamiento" ||
                findViewById<TextView>(R.id.input_score_movie_new).text.toString() == "" ||
                (!findViewById<RadioButton>(R.id.rb_oscar_true_movie_new).isChecked
                        && !findViewById<RadioButton>(R.id.rb_oscar_false_movie_new).isChecked))
    }
}