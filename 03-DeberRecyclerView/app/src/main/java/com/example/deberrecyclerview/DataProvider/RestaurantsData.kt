package com.example.deberrecyclerview.DataProvider

import com.example.deberrecyclerview.entities.Dish
import com.example.deberrecyclerview.entities.Restaurant

class RestaurantsData {
    companion object{
        val dishesDunkin = arrayListOf(
            Dish("Iced Latte","Despierta tus sentidos y refréscate con nuestro " +
                    "famoso iced latte dunkin.",3.50,
                "https://images.rappi.com.ec/products/5d398331-a011-4d50-ba14-c09ec14578f6-1665595593439.png?e=webp&d=511x511&q=85"),
            Dish("Combo Cubanito + Capuccino", "Incluye: cubanito a elección + capuccino 8 oz.",
            3.99,"https://images.rappi.com.ec/products/21326903-ed70-47d8-b734-3eb3a4337680-1686330755762.png?e=webp&d=511x511&q=85")
        )

        val dishesInka = arrayListOf(
            Dish("Inka con Queso","150gr de carne, queso queddar, salsas de la casa, vegetales.",
            6.93,"https://images.rappi.com.ec/products/a075a94e-0c3d-4774-ac61-b7b0b8d8a9f2-1650653157351.png?e=webp&d=511x511&q=85"),
            Dish("Inka Atahualpa", "Hamburguesa atahualpa 2 carnes, queso cheddar," +
                    " cebolla caramelizada, tocino, salsa bbq, champiñones salteados, salsas de la casa, vegetales.",
            9.77, "https://images.rappi.com.ec/products/d56a5585-af93-468b-be6c-f2b3b51c955d-1650652605141.png?e=webp&d=511x511&q=85")
        )


        val restaurantsList = arrayListOf(
            Restaurant("Dunkin Donuts",41,1.79,
        "https://images.rappi.com.ec/restaurants_background/dunk-1660258709533-1685118307644.jpg?e=webp&d=700x100&q=10",
        "https://images.rappi.com.ec/restaurants_logo/dunkimlogo-1649199034771-1685118306605.png?e=webp&d=10x10&q=10",
        dishesDunkin),
            Restaurant("Inka Burger", 41,2.19,
                "https://images.rappi.com.ec/restaurants_background/inkaburger-1662580787171.jpg?e=webp&q=40&d=300x300",
            "https://images.rappi.com.ec/restaurants_logo/inka-logo-1611321923491-1613153127094.png?d=10x10&q=10&e=webp",
            dishesInka)
        )

    }
}