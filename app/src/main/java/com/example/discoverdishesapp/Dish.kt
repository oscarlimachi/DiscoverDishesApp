package com.example.discoverdishesapp

import com.google.gson.annotations.SerializedName

data class Dish(
    val id: Int,
    val name: String,  // Nombre del plato
    val ingredients: List<String>,  // Ingredientes
    val instructions: List<String>,  // Instrucciones
    val prepTimeMinutes: Int,  // Tiempo de preparación
    val cookTimeMinutes: Int,  // Tiempo de cocción
    val servings: Int,  // Número de porciones
    val difficulty: String,  // Dificultad
    val cuisine: String,  // Tipo de cocina
    val caloriesPerServing: Int,  // Calorías por porción
    val tags: List<String>,  // Etiquetas
    val userId: Int,  // ID del usuario
    val image: String,  // URL de la imagen
    val rating: Float,  // Puntuación del plato
    val reviewCount: Int,  // Número de reseñas
    val mealType: List<String>  // Tipo de comida (desayuno, cena, etc.)
)
data class DishSearchResponse(
    val recipes: List<Dish>
)
