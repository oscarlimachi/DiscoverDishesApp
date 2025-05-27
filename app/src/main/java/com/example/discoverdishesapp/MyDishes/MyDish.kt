package com.example.discoverdishesapp.MyDishes

data class MyDish(
var id: Long = 0,
var name: String = "",
var ingredients: String = "",
var instructions: String = "",
var difficult: String = "",
var time: String = "",
var rating: String = "",
var image: ByteArray = ByteArray(0)
)
{
    companion object{
        const val TABLE_NAME = "MyDish"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_NAME = "title"
        const val COLUMN_NAME_INGREDIENTS = "ingredients"
        const val COLUMN_NAME_INSTRUCTIONS = "instructions"
        const val COLUMN_NAME_DIFFICULT = "difficult"
        const val COLUMN_NAME_TIME = "time"
        const val COLUMN_NAME_RATING = "rating"
        const val COLUMN_NAME_IMAGE = "image"

    }
}