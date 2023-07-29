package com.example.deberrecyclerview.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deberrecyclerview.Adapter.RestaurantAdapter
import com.example.deberrecyclerview.DataProvider.RestaurantsData
import com.example.deberrecyclerview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_restaurants)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RestaurantAdapter(RestaurantsData.restaurantsList)
    }
}