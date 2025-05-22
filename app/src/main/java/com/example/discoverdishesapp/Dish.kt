package com.example.discoverdishesapp

import com.google.gson.annotations.SerializedName

data class Dish(
    val id : String,
    val name : String,
    val image : String
    //val ingredients : List<String>,
    //val instructions : List<String>,
    // @SerializedName("preparation") val prepTimeMinutes : Int,
    //val difficulty: String,
    // val cuisine : String,
    //calcular el total racion*calorias por racion
    // val servings : Int,
    // val caloriesPerServing : Int,
    //url

    //val rating: Float
)
data class DishResponse(
    val result: Result<Dish>
)
