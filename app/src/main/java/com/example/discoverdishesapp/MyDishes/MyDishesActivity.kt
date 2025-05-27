package com.example.discoverdishesapp.MyDishes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discoverdishesapp.R
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

        myDishesList=myDishDAO.findAll()
        adapter = MyDishesAdapter(myDishesList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)



    }
}