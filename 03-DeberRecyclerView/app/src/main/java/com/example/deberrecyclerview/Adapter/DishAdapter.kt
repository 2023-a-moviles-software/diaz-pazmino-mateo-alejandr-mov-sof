package com.example.deberrecyclerview.Adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deberrecyclerview.entities.Dish
import com.example.deberrecyclerview.R

class DishAdapter(private val dishesList: List<Dish>): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nameDish: TextView = view.findViewById(R.id.tv_dish_name)
        val descriptionDish: TextView = view.findViewById(R.id.tv_dish_description)
        val priceDish: TextView = view.findViewById(R.id.tv_dish_price)
        val dishImage: ImageView = view.findViewById(R.id.iv_dish_image)

        fun render(dish: Dish){
            nameDish.text = dish.dishName
            descriptionDish.text = dish.description
            priceDish.text = "$${dish.price}"
            Glide.with(dishImage.context).load(dish.dishImage).into(dishImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DishViewHolder(layoutInflater.inflate(R.layout.item_dish,parent,false))
    }

    override fun getItemCount(): Int = dishesList.size

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val item = dishesList[position]
        holder.render(item)
    }
}