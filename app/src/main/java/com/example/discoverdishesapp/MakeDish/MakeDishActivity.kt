package com.example.discoverdishesapp.MakeDish

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.MyDish
import com.example.discoverdishesapp.MyDishDAO
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityMakeDishBinding

class MakeDishActivity : AppCompatActivity() {

    lateinit var binding: ActivityMakeDishBinding
    var myDish: MyDish = MyDish()


    lateinit var myDishDAO: MyDishDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMakeDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myDishDAO= MyDishDAO(this)



        binding.nameMyDishEditText.setText(myDish.name)
        binding.ingredientsMyDishEditText.setText(myDish.ingredients)
        binding.instructionsMyDishEditText.setText(myDish.instructions)
        binding.difficultMyDishEditText.setText(myDish.difficult)
        binding.timeMyDishEditText.setText(myDish.time)
        binding.ratingMyDishEditText.setText(myDish.rating)

        binding.saveButton.setOnClickListener {
            val name = binding.nameMyDishEditText.text.toString()
            myDish.name = name
            val ingredients = binding.ingredientsMyDishEditText.text.toString()
            myDish.ingredients = ingredients
            val instructions = binding.instructionsMyDishEditText.text.toString()
            myDish.instructions = instructions
            val difficult = binding.difficultMyDishEditText.text.toString()
            myDish.difficult = difficult
            val time = binding.timeMyDishEditText.text.toString()
            myDish.time = time
            val rating = binding.ratingMyDishEditText.text.toString()
            myDish.rating = rating


            /*if (name.isBlank() || ingredients.isBlank()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/

            myDishDAO.insert(myDish)


            finish()
        }


    }
}