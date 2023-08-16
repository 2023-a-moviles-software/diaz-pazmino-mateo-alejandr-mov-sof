package com.example.debersqlite.Activies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.debersqlite.CRUD.MovieDAO
import com.example.debersqlite.DB.DataBase
import com.example.debersqlite.R
import com.example.debersqlite.entities.Movie
import com.google.android.material.snackbar.Snackbar

class MoviesView : AppCompatActivity() {
    //var genresArray = DataBase.genresArray
    var moviesArray = arrayListOf<Movie>()
    var idSelectedItem = 0
    var indexGenre = 0
    lateinit var adapter: ArrayAdapter<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_view)
        indexGenre = intent.getIntExtra("index",0)

        moviesArray = DataBase.tableMovie?.getAllByGenre(indexGenre)!!
        val listView: ListView = findViewById(R.id.lv_movies)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,moviesArray)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val item:Movie = listView.adapter.getItem(position) as Movie
            showMovieInfo(item.movieId)
        }
        adapter.notifyDataSetChanged()
        registerForContextMenu(listView)
        val createMovieButton = findViewById<Button>(R.id.btn_crear_pelicula)
        createMovieButton.setOnClickListener { openParameterActivity(CreateMoviesView::class.java) }
    }

    override fun onResume() {
        super.onResume()
        updateListView()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.movie_editar -> {
                openParameterActivity(EditMoviesView::class.java)
                return true
            }
            R.id.movie_eliminar -> {
                DataBase.tableMovie?.delete(idSelectedItem)
                showSnackbar("Película Eliminada")
                updateListView()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.movie_menu,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val movieItem: Movie = adapter.getItem(info.position) as Movie
        idSelectedItem = movieItem.movieId
    }

    fun openParameterActivity(clase: Class<*>){
        val intent = Intent(this,clase)
        intent.putExtra("indexGenre",indexGenre)
        intent.putExtra("indexMovie",idSelectedItem)
        startActivity(intent)
    }

    fun updateListView(){
        val listView: ListView = findViewById(R.id.lv_movies)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,DataBase.tableMovie?.getAllByGenre(indexGenre)!!)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun showSnackbar(text: String){
        Snackbar.make(findViewById(R.id.cl_movies_view),text, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()

    }

    fun showMovieInfo(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Información de la Película")
        builder.setPositiveButton("Volver",null)
        builder.setMessage(DataBase.tableMovie?.getById(id)?.getInfo())
        val dialogo = builder.create()
        dialogo.show()
    }

}