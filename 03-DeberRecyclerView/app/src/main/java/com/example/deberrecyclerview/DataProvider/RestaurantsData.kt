package com.example.deberrecyclerview.DataProvider

import com.example.deberrecyclerview.entities.Dish
import com.example.deberrecyclerview.entities.Restaurant

class RestaurantsData {
    companion object{
        private val dishesInka = arrayListOf(
            Dish("Inka con Queso","150gr de carne, queso queddar, salsas de la casa, vegetales.",
            6.93,"https://images.rappi.com.ec/products/a075a94e-0c3d-4774-ac61-b7b0b8d8a9f2-1650653157351.png?e=webp&d=511x511&q=85"),
            Dish("Inka Atahualpa", "Hamburguesa atahualpa 2 carnes, queso cheddar," +
                    " cebolla caramelizada, tocino, salsa bbq, champiñones salteados, salsas de la casa, vegetales.",
            9.77, "https://images.rappi.com.ec/products/d56a5585-af93-468b-be6c-f2b3b51c955d-1650652605141.png?e=webp&d=511x511&q=85"),
            Dish("Inka Jack Daniel´s","Hamburguesa en salsa Jack Daniels, tocino, queso provolone, vegetales. Papas medianas y bebida a elección.",
                8.51,"https://images.rappi.com.ec/products/49e292a9-2eb4-4b50-8310-a5cb3876c17d-1650653353006.png?e=webp&d=511x511&q=85"),
            Dish("Inka Piña","Hamburguesa con piña asada, salsa bbq de piña, tocino, queso provolone, vegetales.Papas medianas, bebida a elección.",
                8.51,"https://images.rappi.com.ec/products/de490e1b-ca19-441f-b46b-c950f2d06c36-1650653518035.png?e=webp&d=511x511&q=85"),
            Dish("Inka Crispy Chicken","Hamburguesa con pechuga de pollo crujiente con pan de papa, queso cheddar, pickles y vegetales.",
                8.81,"https://images.rappi.com.ec/products/6e28cae2-a008-4647-b0c2-0fee1b355ff1-1650651593145.png?e=webp&d=511x511&q=85"),
            Dish("Alitas X 12","Alitas apanadas con salsa a elección",15.11,
                "https://images.rappi.com.ec/products/8eda81f8-13e7-480e-a607-59faf8066752-1650651158439.png?e=webp&d=511x511&q=85"),
            Dish("Hamburguesa Inkajita","Hamburguesa con carne, queso americano, papas fritas, jugo del valle a elección y sorpresa.",
                6.29,"https://images.rappi.com.ec/products/b26498a5-470f-4e37-befb-b4c6b0d8a9a3-1650650734737.png?e=webp&d=511x511&q=85")
        )

        private val dishesKFC = arrayListOf(
            Dish("Twister Para Dos","2 Twisters + 6 alitas picantes + 2 papas fritas pequeñas + 1 gaseosa 1l",
            16.99,"https://images.rappi.com.ec/products/tmp137672077426014816932510309.png?e=webp&d=511x511&q=85"),
            Dish("2 Sundaes","Dos sundaes, cada uno contiene helado soft de vainilla con tu topping favorito frutilla, mora, chocolate o manjar.",
            3.99,"https://images.rappi.com.ec/products/tmp137672417066954243430079699.png?e=webp&d=511x511&q=85"),
            Dish("12 Presas Kfc","12 Presas de pollo",19.99,
            "https://images.rappi.com.ec/products/tmp137674048886411506653121277.png?e=webp&d=511x511&q=85"),
            Dish("Festin Sin Igual","9 Presas de pollo + 1 papa frita grande y gratis gaseosa de 1l",
            18.99,"https://images.rappi.com.ec/products/tmp1402865516673092335753004029.png?e=webp&d=511x511&q=85"),
            Dish("Combo Ideal","3 Presas de pollo + 1 papa frita pequeña + 1 gaseosa 355ml",
            7.25,"https://images.rappi.com.ec/products/tmp137675974165253594302564727.png?e=webp&d=511x511&q=85"),
            Dish("Papa Frita Grande","Papa frita grande",3.50,
                "https://images.rappi.com.ec/products/tmp966193612123452347493476970.png?e=webp&d=511x511&q=85"),
            Dish("2 Avalanchas","Dos avalanchas, cada una contiene helado soft de vainilla con topping de manjar y galleta oreo troceada",
                3.99,"https://images.rappi.com.ec/products/tmp137672391003898901047734939.png?e=webp&d=511x511&q=85")
        )

        private val dishesPizzaHut = arrayListOf(
            Dish("Lover's Box","Lover box rappi",17.99,
                "https://images.rappi.com.ec/products/tmp16286658405585790741012946.png?e=webp&d=511x511&q=85"),
            Dish("Triple Box 1","2 Pizzas medianas clásicas + 2 complementos (nachos con queso y cinnamon sticks)",
                25.99,"https://images.rappi.com.ec/products/tmp162866299403947015799888413.png?e=webp&d=511x511&q=85"),
            Dish("Combo Mediana","Pizza mediana clasica + gaseosa 1.6",22.68,
                "https://images.rappi.com.ec/products/tmp1628666715919550632998894795.png?e=webp&d=511x511&q=85"),
            Dish("2 Medianas Lover's","2 Medianas lover's",39.98,
                "https://images.rappi.com.ec/products/tmp1628667415864522094301492771.png?e=webp&d=511x511&q=85"),
            Dish("Pizza Veggie Lover's","Para los amantes de los vegetales: tomate, pimiento, cebolla, aceitunas, champiñones y sweet corn.",
                19.99,"https://images.rappi.com.ec/products/tmp162867013132529351377531833.png?e=webp&d=511x511&q=85"),
            Dish("Pizza Chicken Bbq Suprema","La chicken bbq suprema es inigualable: fajitas de pollo, tocino, cebolla, champiñón y salsa bbq.",
                28.50,"https://images.rappi.com.ec/products/tmp162867806324397139919264237.png?e=webp&d=511x511&q=85"),
            Dish("Nachos Con Salsa De Queso","Nachos crujientes acompañados de una exquisita salsa de queso.",
                2.99,"https://images.rappi.com.ec/products/tmp162868336797429954904807475.png?e=webp&d=511x511&q=85v")
        )

        private val dishesSweet = arrayListOf(
            Dish("Dulce de Tres Leches","Bizcocho bañado en crema de leche, leche condensada y evaporada.",
                3.90,"https://images.rappi.com.ec/products/95dfcbfc-ff05-429f-9783-39501b19a949-1673014500351.png?e=webp&d=511x511&q=85"),
            Dish("Torta Mousse de Chocolate y Manjar","Bizcocho de chocolate ecuatoriano bañado en salsa, relleno de mousse y espeso manjar.",
                3.30,"https://images.rappi.com.ec/products/product-1587699383371.png?e=webp&d=511x511&q=85"),
            Dish("Cheesecake de Frambuesas","Cremoso cheesecake de vainilla cubierto con azúcar impalpable y frambuesas frescas.",
                4.25,"https://images.rappi.com.ec/products/1116048-1611157218852.png?e=webp&d=511x511&q=85"),
            Dish("Brownie","Negrito elaborado a base de chocolate ecuatoriano semi amargo.",
                1.80,"https://images.rappi.com.ec/products/product-1587170368632.png?e=webp&d=511x511&q=85"),
            Dish("Cortado","Café espresso con una pequeña cantidad de nuestra fórmula de leche y espuma.",
                2.00,"https://images.rappi.com.ec/products/3a29d513-7c86-40af-9fe9-4c5de457b824-1645550153576.png?e=webp&d=511x511&q=85"),
            Dish("Moca Latte Frío","Nuestra fórmula perfecta de espresso frío, leche y hielo, endulzado y decorado con jalea de chocolate.",
                2.65,"https://images.rappi.com.ec/products/8cbb7fd7-1ac4-4997-b924-74d438fed647-1678206005683.png?e=webp&d=511x511&q=85"),
            Dish("Matcha Greentea Latte Caliente","Té de matcha ceremonial mezclado con leche deslactosada y endulzado con azúcar blanca.",
                2.00,"https://images.rappi.com.ec/products/930d838b-cd5a-4dc3-8282-6bcb3a42b559-1645735099721.png?e=webp&d=511x511&q=85")
        )

        private val dishesTablita = arrayListOf(
            Dish("Mixto Tablita","1 Filete de Res + 1 Filete de Pollo + 1 Porción de papas xl + 2 ensaladas.",
                13.99,"https://images.rappi.com.ec/products/7de24a32-bcb7-45da-a610-1a1b2a1813bd-1659453326573.png?e=webp&d=511x511&q=85"),
            Dish("Bondiola de Cerdo","Bondiola de cerdo corte con ensalada de lechuga, tomate, pimiento, vinagreta de la casa y acompañamiento a elegir.",
                9.99,"https://images.rappi.com.ec/products/1118262-1611774407768.png?e=webp&d=511x511&q=85"),
            Dish("Lomo a la Pimienta","Ya lo probaste? delicioso y tierno lomo fino importado con salsa de pimienta.",
                14.99,"https://images.rappi.com.ec/products/5bed4f55-523f-46f7-bc83-93cf170778d2-1641928017874.png?e=webp&d=511x511&q=85"),
            Dish("Combo Hamburguesa Doble Res","1 Hamburguesa de res doble carne, doble queso con salsas verde, roja, acompañamiento y 1 bebida a elegir.",
                8.99,"https://images.rappi.com.ec/products/1118292-1611774734728.png?e=webp&d=511x511&q=85"),
            Dish("Arroz y Menestra","Arroz porción individual con menestra.",
                1.99,"https://images.rappi.com.ec/products/1118296-1611774650665.png?e=webp&d=511x511&q=85"),
            Dish("Papas Fritas","Papas fritas de la casa porción individual.",
                2.99,"https://images.rappi.com.ec/products/ac85068b-ee58-4a24-b93a-2a28b3ea647b-1630588400956.png?e=webp&d=511x511&q=85"),
            Dish("Choclo con Queso","Choclo con Queso",
                1.99,"https://images.rappi.com.ec/products/1120120-1611938225860.png?e=webp&d=511x511&q=85")
        )


        val restaurantsList = arrayListOf(
            Restaurant("Inka Burger", 41,2.19,4.4,
                "https://images.rappi.com.ec/restaurants_background/inkaburger-1662580787171.jpg?e=webp&q=40&d=300x300",
            "https://images.rappi.com.ec/restaurants_logo/inka-logo-1611321923491-1613153127094.png?d=10x10&q=10&e=webp",
            dishesInka),
            Restaurant("KFC",29,1.19,4.3,
            "https://images.rappi.com.ec/restaurants_background/kfc-1660322660841.jpg?e=webp&q=40&d=300x300",
            "https://images.rappi.com.ec/restaurants_logo/download-1-1614890195344.png?d=10x10&q=10&e=webp",
            dishesKFC),
            Restaurant("Pizza Hut",39,1.59,4.1,
                "https://images.rappi.com.ec/restaurants_background/pizzahut-1661290105583.jpg?e=webp&q=40&d=300x300",
                "https://images.rappi.com.ec/restaurants_logo/logo-1617832340261.jpg?e=webp&d=10x10&q=10",
                dishesPizzaHut),
            Restaurant("Sweet & Coffee",46,1.59,4.6,
                "https://images.rappi.com.ec/restaurants_background/sweet-1673292983309.png?e=webp&q=40&d=300x300",
                "https://images.rappi.com.ec/restaurants_logo/vfgb-vfv-1587679961156.png?e=webp&d=100x100&q=80",
                dishesSweet),
            Restaurant("La Tablita del Tártaro",52,1.79,4.2,
                "https://images.rappi.com.ec/restaurants_background/latablitadeltartaro-1661809325570.jpg?e=webp&q=40&d=300x300",
                "https://images.rappi.com.ec/restaurants_logo/la-tablita-logo-1-1611774096077.png?d=10x10&q=10&e=webp",
                dishesTablita)
        )

    }
}