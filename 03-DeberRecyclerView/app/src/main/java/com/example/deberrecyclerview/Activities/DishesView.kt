package com.example.deberrecyclerview.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deberrecyclerview.Adapter.DishAdapter
import com.example.deberrecyclerview.DataProvider.RestaurantsData
import com.example.deberrecyclerview.R
import com.example.deberrecyclerview.entities.Restaurant

class DishesView : AppCompatActivity() {
    var indexRestaurant = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_view)
        indexRestaurant = intent.getIntExtra("index",0)
        val restaurant = RestaurantsData.restaurantsList[indexRestaurant]
        fillRestaurantData(restaurant)
        val arrowBack = findViewById<CardView>(R.id.cv_arrow_back)
        arrowBack.setOnClickListener {  finish()}
        initRecyclerView()
    }

    private fun fillRestaurantData(restaurant: Restaurant){
        val restaurantImage = findViewById<ImageView>(R.id.iv_restaurant_image_dishes)
        val restaurantIcon = findViewById<ImageView>(R.id.iv_restaurant_icon_dishes)
        findViewById<TextView>(R.id.tv_restaurant_name_dishes).text = restaurant.nameRestaurant
        findViewById<TextView>(R.id.tv_restaurant_delivery_time_dishes).text = "${restaurant.deliveryTime} min"
        findViewById<TextView>(R.id.tv_restaurant_delivery_fee_dishes).text = "$${restaurant.deliveryCost}"
        findViewById<TextView>(R.id.tv_restaurant_rating_dishes).text = restaurant.rate.toString()
        Glide.with(restaurantImage.context).load(restaurant.restaurantImage).into(restaurantImage)
        Glide.with(restaurantIcon.context).load(restaurant.restaurantIcon).into(restaurantIcon)
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_dishes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DishAdapter(RestaurantsData.restaurantsList[indexRestaurant].dishes)
    }
}