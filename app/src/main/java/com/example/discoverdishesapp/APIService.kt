package com.example.discoverdishesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {

    @GET("recipes/search")
    suspend fun findDishByName(@Query("q") name: String): DishSearchResponse
    companion object{
        //servidor llamada
        fun getInstance() : APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIService::class.java)

        }
    }
}