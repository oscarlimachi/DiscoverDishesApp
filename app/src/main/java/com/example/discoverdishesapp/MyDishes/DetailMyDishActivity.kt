package com.example.discoverdishesapp.MyDishes

import android.R.attr.bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.DishesList.Dish
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityDetailMyDishBinding


class DetailMyDishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMyDishBinding
    private lateinit var myDishDAO: MyDishDAO
    private lateinit var myDish: MyDish


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMyDishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Obtener el ID del intent

        val dishId = intent.getLongExtra("MY_DISH_ID", -1)
        if (dishId != -1L) {
            // 2. Obtener el plato desde la base de datos
            myDishDAO = MyDishDAO(this)
            myDish = myDishDAO.findById(dishId)!!

                // 3. Mostrar los datos en el layout
                supportActionBar?.title = myDish.name
                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val imageBytes = myDish.image

                if (imageBytes != null && imageBytes.isNotEmpty()) {
                    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    binding.myDishDetailImageView.setImageBitmap(bitmap)
                } else {
                    // Imagen por defecto si no hay imagen guardada
                    binding.myDishDetailImageView.setImageResource(R.drawable.ic_launcher_background)
                }


                binding.myDishBottomNavigationView.setOnItemSelectedListener { menuItem ->
                    binding.contentmyDishIngredients.root.visibility = View.GONE
                    binding.myDishInstructionsContentTextView.root.visibility = View.GONE

                    when (menuItem.itemId) {
                        R.id.myDishMenuIngredients -> binding.contentmyDishIngredients.root.visibility =
                            View.VISIBLE

                        R.id.myDishMenuInstructions -> binding.myDishInstructionsContentTextView.root.visibility =
                            View.VISIBLE
                    }
                    true
                }
                /*forzamos para a pulsar click para que se oculte*/
                binding.myDishBottomNavigationView.selectedItemId = R.id.myDishMenuIngredients
        } else {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mydish, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_my_dish -> {
                myDishDAO.delete(myDish)
                finish()
                return true
            }

            R.id.menu_edit_my_dish -> {
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }


    }

}
