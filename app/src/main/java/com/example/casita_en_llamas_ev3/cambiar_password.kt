package com.example.casita_en_llamas_ev3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.casita_en_llamas_ev3.databinding.ActivityCambiarPasswordBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class cambiar_password : AppCompatActivity() {

    private lateinit var binding:ActivityCambiarPasswordBinding

    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarPasswordBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth= Firebase.auth

        binding.btnCambiarPassword.setOnClickListener {

            val user= Firebase.auth.currentUser!!

            val ContraActual:String=binding.etContraActual.text.toString()
            val ContraNueva:String=binding.etNewPassword.text.toString()
            val ContraNueva2:String=binding.etNewPassword2.text.toString()

            if(ContraActual.isEmpty()){
                binding.etContraActual.error="Por favor ingrese su contraseña actual"
                return@setOnClickListener
            }
            if(ContraNueva.isEmpty()){
                binding.etNewPassword.error="Por favor ingrese su nueva contraseña"
                return@setOnClickListener
            }
            if(ContraNueva2.isEmpty()){
                binding.etNewPassword2.error="Por favor confirme su nueva contraseña"
                return@setOnClickListener
            }
            val credential=EmailAuthProvider
                .getCredential(user!!.email.toString(),ContraActual)

            user.reauthenticate(credential)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Cambiando la Contraseña", Toast.LENGTH_LONG).show()
                        if(ContraNueva==ContraNueva2){
                            user.updatePassword(ContraNueva)
                               .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this,"Contraseña cambiada correctamente", Toast.LENGTH_LONG).show()
                                        finish()
                                    } else {
                                        Toast.makeText(this,"Error al cambiar la contraseña", Toast.LENGTH_LONG).show()
                                    }
                                }


                        }else{
                            Toast.makeText(this,"Contraseñas no coiciden",Toast.LENGTH_LONG).show()


                        }

                    }else{
                        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()

                    }




                }









        }




    }
}