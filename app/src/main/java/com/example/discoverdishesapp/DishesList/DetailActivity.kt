package com.example.discoverdishesapp.DishesList

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    companion object{
        const val DISH_ID = "DISH_ID"
    }
    lateinit var binding: ActivityDetailBinding
    lateinit var dish: Dish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Barra menu
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(DISH_ID)!!
        getDishById(id)

        //invocar a la navegacion y recibir pulsar
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem->
            binding.contentIngredients.root.visibility = View.GONE
            binding.contentInstructions.root.visibility = View.GONE
            binding.contentDetail.root.visibility = View.GONE

            when(menuItem.itemId){
                R.id.menuIngredients -> binding.contentIngredients.root.visibility = View.VISIBLE
                R.id.menuInstructions -> binding.contentInstructions.root.visibility = View.VISIBLE
                R.id.menuDetails -> binding.contentDetail.root.visibility = View.VISIBLE
            }
            true
        }
        /*forzamos para a pulsar click para que se oculte*/
        binding.bottomNavigationView.selectedItemId = R.id.menuIngredients



    }

    //recuperamos id para utilar datos
    private fun getDishById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = APIService.getInstance()
                dish = service.findDishById(id)
                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }


            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    //aqui utilizamos los datos
    fun loadData(){
        supportActionBar?.title=dish.name
        Picasso.get().load(dish.image).into(binding.detailImageView)

        binding.contentIngredients.ingredientsTextView.text = dish.ingredients.joinToString("\n")
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