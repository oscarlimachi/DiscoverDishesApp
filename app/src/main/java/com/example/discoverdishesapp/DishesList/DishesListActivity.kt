package com.example.discoverdishesapp.DishesList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityDishesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.view.MenuItem


class DishesListActivity : AppCompatActivity() {
    lateinit var binding: ActivityDishesListBinding
    lateinit var adapter: AdapterDishes

    var dishList: List<Dish> = emptyList()
    //falta lista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDishesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        adapter = AdapterDishes(dishList, { position ->
            val dish = dishList[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("DISH_ID", dish.id)
            startActivity(intent)
        })
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        searchDish("a")
        supportActionBar?.title= "RecipesList"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#A4F7AC44")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main,menu)

        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchDish(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false            }
        })

        return true
    }
    fun searchDish(query: String) {
        // Llamada en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = APIService.getInstance()
                val response = service.findDishByName(query)
                dishList = response.recipes

                // Volvemos al hilo principal
                CoroutineScope(Dispatchers.Main).launch {
                    adapter.updateItems(dishList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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