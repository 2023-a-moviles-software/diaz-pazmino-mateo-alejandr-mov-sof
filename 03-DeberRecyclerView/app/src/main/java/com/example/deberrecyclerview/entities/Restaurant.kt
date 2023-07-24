package com.example.deberrecyclerview.entities

data class Restaurant (
    val nameRestaurant: String,
    val deliveryTime: Int,
    val deliveryCost: Double,
    val rate: Double,
    val restaurantImage: String,
    val restaurantIcon: String,
    val dishes: ArrayList<Dish>
)
