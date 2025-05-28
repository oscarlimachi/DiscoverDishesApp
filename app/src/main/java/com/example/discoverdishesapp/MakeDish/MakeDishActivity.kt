package com.example.discoverdishesapp.MakeDish

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore

import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.MyDishes.MyDish
import com.example.discoverdishesapp.MyDishes.MyDishDAO
import com.example.discoverdishesapp.R
import com.example.discoverdishesapp.databinding.ActivityMakeDishBinding
import java.io.ByteArrayOutputStream

class MakeDishActivity : AppCompatActivity() {


    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    lateinit var binding: ActivityMakeDishBinding
    var myDish: MyDish = MyDish()

    companion object {
        const val REQUEST_IMAGE_PICK = 1
    }

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


        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val imageUri = data?.data
                imageUri?.let {
                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(contentResolver, it)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        MediaStore.Images.Media.getBitmap(contentResolver, it)
                    }

                    // Mostrar en ImageView
                    binding.prueba.setImageBitmap(bitmap)
                    // Guardar la imagen como ByteArray en myDish
                    myDish.image = bitmapToByteArray(bitmap)


                }
            }
        }



        myDishDAO= MyDishDAO(this)

        binding.nameMyDishEditText.setText(myDish.name)
        binding.ingredientsMyDishEditText.setText(myDish.ingredients)
        binding.instructionsMyDishEditText.setText(myDish.instructions)
        binding.difficultMyDishEditText.setText(myDish.difficult)
        binding.timeMyDishEditText.setText(myDish.time)
        binding.ratingMyDishEditText.setText(myDish.rating)



        //boton de imagen
        binding.takeImageButton.setOnClickListener {
            val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(pickIntent)

        }

        binding.saveButton.setOnClickListener {

                val name = binding.nameMyDishEditText.text?.toString()?.trim()
                val ingredients = binding.ingredientsMyDishEditText.text?.toString()?.trim()
                val instructions = binding.instructionsMyDishEditText.text?.toString()?.trim()
                val difficult = binding.difficultMyDishEditText.text?.toString()?.trim()
                val time = binding.timeMyDishEditText.text?.toString()?.toIntOrNull() ?: 0
                val rating = binding.ratingMyDishEditText.text?.toString()?.toFloatOrNull() ?: 0f


            /*if (name.isBlank() || ingredients.isBlank()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/

            myDishDAO.insert(myDish)


            finish()
        }

    }
    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

}