package com.example.casita_en_llamas_ev3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.casita_en_llamas_ev3.Models.Termohigrometro
import com.example.casita_en_llamas_ev3.databinding.ActivityMostrarTermohigrometroBinding
import com.google.firebase.database.*

class mostrar_termohigrometro : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarTermohigrometroBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMostrarTermohigrometroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia a la base de datos
        database = FirebaseDatabase.getInstance().getReference("Termohigrometro")

        // Escuchar cambios en tiempo real
        database.orderByKey().limitToLast(1).addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val termohigrometro = data.getValue(Termohigrometro::class.java)
                    if (termohigrometro != null) {
                        binding.tvHora.text = "Hora: ${termohigrometro.hora}"
                        binding.tvFecha.text = "Fecha: ${termohigrometro.fecha}"
                        binding.tvHumedad.text = "Humedad: ${termohigrometro.humedad}"
                        binding.tvHumo.text = "Humo: ${termohigrometro.humo}"
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, inicio_app::class.java)
            startActivity(intent)
        }

        binding.btnVolverDatos.setOnClickListener {
            val intent = Intent(this, datos_de_humo::class.java)
            startActivity(intent)
        }

    }
}
