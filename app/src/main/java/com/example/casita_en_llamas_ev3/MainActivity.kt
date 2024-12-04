package com.example.casita_en_llamas_ev3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.casita_en_llamas_ev3.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val correo = binding.etEmail.text.toString()
            val clave = binding.etPassword.text.toString()

            if (correo.isEmpty()) {
                binding.etEmail.error = "Por favor ingrese un correo"
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                binding.etEmail.error = "Por favor ingrese un correo válido"
                return@setOnClickListener
            }
            if (clave.isEmpty()) {
                binding.etPassword.error = "Por favor ingrese la contraseña"
                return@setOnClickListener
            }
            if (clave.length < 6) {
                binding.etPassword.error = "La contraseña debe tener al menos 6 caracteres"
                return@setOnClickListener
            }

            signUp(correo, clave)
        }

        binding.tvRegistrar.setOnClickListener {
            try {
                val intent = Intent(this, registro_app::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al iniciar la actividad", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun signUp(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, inicio_app::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorMessage = task.exception?.localizedMessage ?: "Error desconocido"
                    Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
