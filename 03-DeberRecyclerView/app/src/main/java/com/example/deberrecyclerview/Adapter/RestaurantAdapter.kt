package com.example.deberrecyclerview.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deberrecyclerview.entities.Restaurant
import com.example.deberrecyclerview.R

class RestaurantAdapter(private val restaurantsList: List<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>(){

    inner class RestaurantViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val nameRestaurant: TextView = view.findViewById(R.id.tv_restaurant_name)
        val deliveryTime: TextView = view.findViewById(R.id.tv_restaurant_DeliveryTime)
        val delivevyCost: TextView = view.findViewById(R.id.tv_restaurant_DeliveryCost)
        val restaurantImage: ImageView = view.findViewById(R.id.iv_restaurant_image)
        val restaurantIcon: ImageView = view.findViewById(R.id.iv_restaurant_icon)

        fun render(restaurant: Restaurant){
            nameRestaurant.text = restaurant.nameRestaurant
            deliveryTime.text = restaurant.deliveryTime.toString()
            delivevyCost.text = restaurant.deliveryCost.toString()
            Glide.with(restaurantImage.context).load(restaurant.restaurantImage).into(restaurantImage)
            Glide.with(restaurantIcon.context).load(restaurant.restaurantIcon).into(restaurantIcon)
            itemView.setOnClickListener {  }
        }

        fun openParameterActivity(clase: Class<*>,index: Int){
            val intent = Intent(this,clase)
            intent.putExtra("index",index)
            startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RestaurantAdapter.RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(layoutInflater.inflate(R.layout.item_restaurant, parent,false))
    }

    override fun onBindViewHolder(holder: RestaurantAdapter.RestaurantViewHolder, position: Int) {
        val item = restaurantsList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = restaurantsList.size

}