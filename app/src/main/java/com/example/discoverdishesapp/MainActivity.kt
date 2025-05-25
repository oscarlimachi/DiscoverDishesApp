package com.example.discoverdishesapp

import android.content.Intent
import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.discoverdishesapp.DishesList.AdapterDishes
import com.example.discoverdishesapp.DishesList.DishesListActivity

import com.example.discoverdishesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: AdapterDishes
    var dishList: List<Dish> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        supportActionBar?.title= "Home"

        binding.dishesList.setOnClickListener {
            val intent = Intent(this, DishesListActivity::class.java)
            startActivity(intent)
        }
        binding.createDish.setOnClickListener {
            val intent = Intent(this, MakeDishActivity::class.java)
            startActivity(intent)
        }
        binding.myDishes.setOnClickListener {
            val intent = Intent(this, MyDishesActivity::class.java)
            startActivity(intent)
        }
    }


}