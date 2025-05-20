package com.example.discoverdishesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {



    companion object{
        //servidor llamada
        fun getInstance() : APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/recipes")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIService::class.java)

        }
    }
}