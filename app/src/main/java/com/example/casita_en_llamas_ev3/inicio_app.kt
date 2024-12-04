package com.example.casita_en_llamas_ev3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.casita_en_llamas_ev3.Vistas.configuracionFragment
import com.example.casita_en_llamas_ev3.Vistas.inicioFragment
import com.example.casita_en_llamas_ev3.Vistas.notificacionesFragment
import com.example.casita_en_llamas_ev3.databinding.ActivityInicioAppBinding

class inicio_app : AppCompatActivity() {
    private lateinit var binding:ActivityInicioAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInicioAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,inicioFragment()).commit()
        }

        binding.bottomNavegation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_1->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, inicioFragment()).commit()
                    true
                }
                R.id.item_2->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, notificacionesFragment()).commit()
                    true
                }
                R.id.item_3->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, configuracionFragment()).commit()
                    true
                }
                else -> false
            }









        }





    }
}