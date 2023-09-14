package com.example.examen2b.Activies

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
import androidx.lifecycle.lifecycleScope
import com.example.examen2b.CRUD.GenreDAO
import com.example.examen2b.CRUD.MovieDAO
import com.example.examen2b.DB.DataBase
import com.example.examen2b.R
import com.example.examen2b.entities.Genre
import com.google.android.gms.common.api.Response
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresView : AppCompatActivity() {
    var genresArray = arrayListOf<Genre>()
    var idSelectedItem = 0
    lateinit var adapter: ArrayAdapter<Genre>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.genres_view)

        DataBase.tableGenre = GenreDAO()
        DataBase.tableMovie = MovieDAO()
        lifecycleScope.launch(Dispatchers.IO) {
            val deferred = async { genresArray = DataBase.tableGenre!!.getAll() }
            val response = deferred.await()
            withContext(Dispatchers.Main){
                if (deferred.isCompleted){
                    val listView: ListView = findViewById<ListView>(R.id.lv_genres)
                    adapter = ArrayAdapter(this@GenresView,android.R.layout.simple_list_item_1,genresArray)
                    listView.adapter = adapter
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val item:Genre = listView.adapter.getItem(position) as Genre
                        showGenreInfo(item.genreId)
                    }
                    adapter.notifyDataSetChanged()
                    registerForContextMenu(listView)
                }

            }
        }


        val createButton = findViewById<Button>(R.id.btn_crear)
        createButton.setOnClickListener { openActivity(CreateGenresView::class.java) }
    }

    override fun onResume() {
        super.onResume()
        updateListView()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.genre_editar -> {
                openParameterActivity(EditGenresView::class.java)
                return true
            }
            R.id.genre_eliminar -> {
                DataBase.tableGenre?.delete(idSelectedItem)
                showSnackbar("Genero Eliminado")
                updateListView()
                return true
            }
            R.id.genre_ver_movies -> {
                openParameterActivity(MoviesView::class.java)
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
        inflater.inflate(R.menu.genre_menu,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val genreItem:Genre = adapter.getItem(info.position) as Genre
        idSelectedItem = genreItem.genreId
    }

    fun openActivity(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun openParameterActivity(clase: Class<*>){
        val intent = Intent(this,clase)
        intent.putExtra("index",idSelectedItem)
        startActivity(intent)
    }

    fun updateListView(){
        val listView: ListView = findViewById<ListView>(R.id.lv_genres)
        lifecycleScope.launch(Dispatchers.IO) {
            val deferred = async {  adapter = ArrayAdapter(this@GenresView,android.R.layout.simple_list_item_1,DataBase.tableGenre!!.getAll()) }
            val response = deferred.await()
            withContext(Dispatchers.Main){
                if (deferred.isCompleted){
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }



    fun showSnackbar(text: String){
        Snackbar.make(findViewById(R.id.cl_genres_view),text, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()

    }

    fun showGenreInfo(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Información del Genero")
        builder.setPositiveButton("Volver",null)
        lifecycleScope.launch(Dispatchers.IO) {
            val deferred = async { builder.setMessage(DataBase.tableGenre?.getById(id)?.getInfo()) }
            val response = deferred.await()
            withContext(Dispatchers.Main){
                if(deferred.isCompleted){
                    val dialogo = builder.create()
                    dialogo.show()
                }
            }
        }
    }
}