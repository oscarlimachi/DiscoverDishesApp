package com.example.discoverdishesapp.MyDishes

import android.R.attr.bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityDetailMyDishBinding


class DetailMyDishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMyDishBinding
    private lateinit var myDishDAO: MyDishDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMyDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Obtener el ID del intent
        val dishId = intent.getLongExtra("MY_DISH_ID", -1)

        if (dishId != (-1).toLong()) {
            // 2. Obtener el plato desde la base de datos
            myDishDAO = MyDishDAO(this)
            val myDish = myDishDAO.findById(dishId)

            if (myDish != null) {
                // 3. Mostrar los datos en el layout
                supportActionBar?.title = myDish.name

                val imageBytes = myDish.image

                if (imageBytes != null && imageBytes.isNotEmpty()) {
                    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    binding.myDishDetailImageView.setImageBitmap(bitmap)
                } else {
                    // Imagen por defecto si no hay imagen guardada
                    binding.myDishDetailImageView.setImageResource(R.drawable.ic_launcher_background)
                }


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
                binding.myDishBottomNavigationView.selectedItemId = R.id.myDishMenuIngredients

            } else {
                Toast.makeText(this, "Plato no encontrado", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}
