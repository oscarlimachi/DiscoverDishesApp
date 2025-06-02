package com.example.discoverdishesapp.MyDishes

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityDetailMyDishBinding
import com.example.discoverdishesapp.databinding.ActivityMyDishesBinding

class MyDishesActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyDishesBinding
    lateinit var adapter: MyDishesAdapter

    var myDishesList: List<MyDish> = emptyList()
    lateinit var myDishDAO: MyDishDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityMyDishesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myDishDAO = MyDishDAO(this)


        adapter = MyDishesAdapter(myDishesList, {
            position ->
            val myDish = myDishesList[position]
            val intent = Intent(this, DetailMyDishActivity::class.java)
            intent.putExtra("MY_DISH_ID",myDish.id)
            startActivity(intent)

        })
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


        supportActionBar?.title = "My List Recipe"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#D04343")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onResume() {
        super.onResume()
        myDishesList=myDishDAO.findAll()
        adapter.updateItems(myDishesList)
    }
}