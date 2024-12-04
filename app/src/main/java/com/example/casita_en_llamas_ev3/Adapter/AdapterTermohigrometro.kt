package com.example.casita_en_llamas_ev3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.casita_en_llamas_ev3.Models.Termohigrometro
import com.example.casita_en_llamas_ev3.R

class AdapterTermohigrometro (private var termohigrometro: ArrayList<Termohigrometro> ):
    RecyclerView.Adapter<AdapterTermohigrometro.ViewHolder>(){
        class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val hora: TextView =itemView.findViewById(R.id.etHora)
            val fecha: TextView =itemView.findViewById(R.id.etFecha)
            val humo: TextView =itemView.findViewById(R.id.etHumo)
            val humedad: TextView =itemView.findViewById(R.id.etHumedad)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AdapterTermohigrometro.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_termohigrometro,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val termohigrometro=termohigrometro[position]
        holder.hora.text = termohigrometro.hora
        holder.fecha.text = termohigrometro.fecha
        holder.humo.text = termohigrometro.humo
        holder.humedad.text = termohigrometro.humedad



    }

    override fun getItemCount(): Int {
        return termohigrometro.size

    }
}


