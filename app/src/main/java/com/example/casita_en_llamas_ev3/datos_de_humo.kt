package com.example.casita_en_llamas_ev3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.casita_en_llamas_ev3.Models.Termohigrometro
import com.example.casita_en_llamas_ev3.databinding.ActivityDatosDeHumoBinding
import com.example.casita_en_llamas_ev3.databinding.ActivityRegistroAppBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class datos_de_humo : AppCompatActivity() {

    private lateinit var binding: ActivityDatosDeHumoBinding

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDatosDeHumoBinding.inflate(layoutInflater)


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = FirebaseDatabase.getInstance().getReference("Termohigrometro")

        binding.btnGuardarConfiguracion.setOnClickListener {
            val hora = binding.etHora.text.toString()
            val fecha = binding.etFecha.text
            val humedad = binding.etHumedad.text
            val humo = binding.etHumo.text

            val id = database.child("termohigrometro").push().key

            if (hora.isEmpty()) {
                binding.etHora.error = "Por favor ingrese una hora"
                return@setOnClickListener
            }
            if (fecha.isEmpty()) {
                binding.etFecha.error = "Por favor ingrese una fecha"
                return@setOnClickListener
            }
            if (humedad.isEmpty()) {
                binding.etHumedad.error = "Por favor ingrese la humedad"
                return@setOnClickListener
            }
            if (humo.isEmpty()) {
                binding.etHumo.error = "Por favor ingrese el nivel de humo"
                return@setOnClickListener
            }

            val termohigrometro = Termohigrometro(
                id, hora,
                fecha.toString(), humedad.toString(), humo.toString()

            )
            val intent = Intent(this, mostrar_termohigrometro::class.java)
            startActivity(intent)

            database.child(id!!).setValue(termohigrometro)
                .addOnSuccessListener {
                    binding.etHora.setText("")
                    binding.etFecha.setText("")
                    binding.etHumedad.setText("")
                    binding.etHumo.setText("")
                    Snackbar.make(binding.root,"Configuracion agregada",Snackbar.LENGTH_SHORT).show()

                }






        }
        binding.btnVerConfiguracion.setOnClickListener {
            val intent = Intent(this, mostrar_termohigrometro::class.java)
            startActivity(intent)

        }

        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, inicio_app::class.java)
            startActivity(intent)
        }
    }
}