package com.example.discoverdishesapp.MyDishes

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.MakeDish.MakeDishActivity
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
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#D04343")))
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val imageBytes = myDish.image
            //Imaggen
                if (imageBytes != null && imageBytes.isNotEmpty()) {
                    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    binding.myDishDetailImageView.setImageBitmap(bitmap)
                } else {
                    // Imagen por defecto si no hay imagen guardada
                    binding.myDishDetailImageView.setImageResource(R.drawable.ic_launcher_background)
                }
            //ingredientes
            val ingredients=myDish.ingredients
            val ingredientsList = ingredients.split("\n").map { it.trim() }
            val ingredientFormated = ingredientsList.joinToString(separator = "\n") { "· $it" }
            binding.contentMyDishIngredients.MyDishContentIngredientsTextView.text =ingredientFormated

            //instrucciones
            val instructions = myDish.instructions
            val instructionsList = instructions.split("\n").map { it.trim() }
            val instructionsFormated = instructionsList.joinToString(separator = "\n") { "· $it" }
            binding.contentMyDishInstructions.myDishInstructionsContentTextView.text =instructionsFormated
            binding.myDishBottomNavigationView.setOnItemSelectedListener { menuItem ->
                    binding.contentMyDishIngredients.root.visibility = View.GONE
                    binding.contentMyDishInstructions.root.visibility = View.GONE

                    when (menuItem.itemId) {
                        R.id.myDishMenuIngredients -> binding.contentMyDishIngredients.root.visibility =
                            View.VISIBLE

                        R.id.myDishMenuInstructions -> binding.contentMyDishInstructions.root.visibility =
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
                val intent = Intent(this, MakeDishActivity::class.java)
                intent.putExtra("MY_DISH_ID",myDish.id)
                startActivity(intent)
                return true

            }
            android.R.id.home -> {
                finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }


    }
    override fun onResume() {
        super.onResume()

        // Volvemos a cargar el plato desde la base de datos
        myDish = myDishDAO.findById(myDish.id)!!

        // Actualizamos los datos visibles
        supportActionBar?.title = myDish.name

        if (myDish.image.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeByteArray(myDish.image, 0, myDish.image.size)
            binding.myDishDetailImageView.setImageBitmap(bitmap)
        }

        binding.contentMyDishIngredients.MyDishContentIngredientsTextView.text =
            myDish.ingredients.split("\n").joinToString("\n") { "· ${it.trim()}" }

        binding.contentMyDishInstructions.myDishInstructionsContentTextView.text =
            myDish.instructions.split("\n").joinToString("\n") { "· ${it.trim()}" }
    }


}
