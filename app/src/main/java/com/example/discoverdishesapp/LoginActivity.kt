package com.example.discoverdishesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.discoverdishesapp.databinding.ActivityLoginBinding
import com.example.discoverdishesapp.databinding.DialogCreateAccountBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Configuramos el evento de clic del botón de inicio de sesión
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Validación de los datos ingresados
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Accedemos a SharedPreferences
                val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
                val storedUsername = sharedPreferences.getString("username", null)
                val storedPassword = sharedPreferences.getString("password", null)

                // Verificamos si los datos ingresados coinciden con los guardados
                if (username == storedUsername && password == storedPassword) {
                    // Si la autenticación es correcta, pasamos a la siguiente actividad
                    val intent = Intent(this, MainActivity::class.java)

                    //pasar nombre de usuario al main
                    intent.putExtra("USER_NAME",username)
                    startActivity(intent)




                    finish()
                } else {
                    // Si no coinciden, mostramos un mensaje de error
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.createAccountText.setOnClickListener {
            createAccount()

        }
        supportActionBar?.hide()
    }


    private fun createAccount() {
        val dialogBinding = DialogCreateAccountBinding.inflate(layoutInflater)
        MaterialAlertDialogBuilder(this)
            .setTitle("Crear Cuenta")
            .setView(dialogBinding.root)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                val username = dialogBinding.usernameEditText.text.toString()
                val password = dialogBinding.passwordEditText.text.toString()

                // Validamos que no estén vacíos
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Por favor ingrese ambos campos", Toast.LENGTH_SHORT).show()
                } else {
                    // Guardamos los datos del nuevo usuario en SharedPreferences
                    val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username", username)
                    editor.putString("password", password)
                    editor.apply()

                    Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setIcon(R.drawable.ic_user)
            .show()
    }
}