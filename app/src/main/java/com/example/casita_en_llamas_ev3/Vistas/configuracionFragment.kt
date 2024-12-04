package com.example.casita_en_llamas_ev3.Vistas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.casita_en_llamas_ev3.R
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.Button
import com.example.casita_en_llamas_ev3.MainActivity
import com.example.casita_en_llamas_ev3.cambiar_password
import com.example.casita_en_llamas_ev3.inicio_app
import com.example.casita_en_llamas_ev3.mostrar_termohigrometro

class configuracionFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = inflater.inflate(R.layout.fragment_configuracion, container, false)
        val btnCambiarClave: Button = binding.findViewById(R.id.btnCambiarClave)


        val btnCerrarSesion: Button = binding.findViewById(R.id.btnCerrarsesion)

        btnCerrarSesion.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión, despues de aceptar tendras que volver a iniciar sesion?")
                .setNeutralButton("Cancelar") { dialog, which ->
                }
                .setPositiveButton("Acepto la condicion de cerrar mi sesion") { dialog, which ->
                    signOut()
                }
                .show()
        }

        btnCambiarClave.setOnClickListener {
            val intent = Intent(requireActivity(), cambiar_password::class.java)
            startActivity(intent)
        }



        return binding
    }






    private fun signOut() {
        auth.signOut()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }










}
