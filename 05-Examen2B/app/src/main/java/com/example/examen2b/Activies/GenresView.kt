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
import com.example.examen2b.CRUD.CRUDGenres
import com.example.examen2b.DB.DataBaseMemory
import com.example.examen2b.R
import com.google.android.material.snackbar.Snackbar

class GenresView : AppCompatActivity() {
    val genresArray = DataBaseMemory.genresArray
    var idSelectedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.genres_view)
        val listView: ListView = findViewById<ListView>(R.id.lv_genres)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,genresArray)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            showGenreInfo(position)
        }
        adapter.notifyDataSetChanged()
        registerForContextMenu(listView)
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
                CRUDGenres.delete(idSelectedItem)
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
        val id = info.position
        idSelectedItem = id
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
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,genresArray)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun showSnackbar(text: String){
        Snackbar.make(findViewById(R.id.cl_genres_view),text, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()

    }

    fun showGenreInfo(index: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Información del Genero")
        builder.setPositiveButton("Volver",null)
        builder.setMessage(CRUDGenres.get(index).getInfo())
        val dialogo = builder.create()
        dialogo.show()
    }
}