package com.example.discoverdishesapp

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.discoverdishesapp.DishesList.AdapterDishes
import com.example.discoverdishesapp.DishesList.Dish
import com.example.discoverdishesapp.DishesList.DishesListActivity
import com.example.discoverdishesapp.MakeDish.MakeDishActivity
import com.example.discoverdishesapp.MyDishes.MyDishesActivity

import com.example.discoverdishesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: AdapterDishes
    var dishList: List<Dish> = emptyList()
    companion object{
        const val USER_NAME = "USER_NAME"
    }


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


        supportActionBar?.hide()  // Oculta el Action Bar

        //NOMBRE USUARIO
        val name = intent.getStringExtra(USER_NAME)!!
        binding.usernameTextView.text = name


        binding.menuButton.setOnClickListener {
            //editar y borrar usuario
            val popupMenu = PopupMenu(this, binding.menuButton)
            popupMenu.inflate(R.menu.user_context_menu)
            //id de las 2 opciones
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.actionEdit -> {
                        Toast.makeText(this, "Editar usuario", Toast.LENGTH_SHORT).show()
// Aquí podrías abrir una nueva actividad para editar

                        true
                    }

                    R.id.actionDelete -> {
                        Toast.makeText(this, "Editar usuario", Toast.LENGTH_SHORT).show()
// Aquí podrías abrir una nueva actividad para editar

                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }




        //OPCIONES PRINCIPALES
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
