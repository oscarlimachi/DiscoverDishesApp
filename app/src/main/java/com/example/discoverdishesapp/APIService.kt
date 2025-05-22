package com.example.discoverdishesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {


    @GET("https://dummyjson.com/recipes/search?q={name}")
    suspend fun findDishByName(@Path("name") name:String) :DishResponse

    companion object{
        //servidor llamada
        fun getInstance() : APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/recipes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIService::class.java)

        }
    }
}