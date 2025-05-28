package com.example.discoverdishesapp.MyDishes

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.DishesList.APIService
import com.example.discoverdishesapp.DishesList.Dish
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityDetailBinding
import com.example.discoverdishesapp.databinding.ActivityDetailMyDishBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
   /* companion object{
        const val DISH_ID = "DISH_ID"
    }*/
    lateinit var binding: ActivityDetailMyDishBinding
    lateinit var myDish: MyDish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailMyDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Barra menu
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A4F7AC44")))




        /*val id = intent.getStringExtra(DISH_ID)!!
        getDishById(id)*/

        //invocar a la navegacion y recibir pulsar
        binding.myDishBottomNavigationView.setOnItemSelectedListener { menuItem->
            binding.contentmyDishIngredients.root.visibility = View.GONE
            binding.myDishInstructionsContentTextView.root.visibility = View.GONE


            when(menuItem.itemId){
                R.id.myDishMenuIngredients -> binding.contentmyDishIngredients.root.visibility = View.VISIBLE
                R.id.myDishMenuInstructions -> binding.myDishInstructionsContentTextView.root.visibility = View.VISIBLE
            }
            true
        }
        /*forzamos para a pulsar click para que se oculte*/
        binding.myDishBottomNavigationView.selectedItemId = R.id.menuIngredients
    }


    //aqui utilizamos los datos
    fun loadData(){
        supportActionBar?.title=myDish.name

        //mostrar datos del base de datos

    }


    //volver atrás
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}