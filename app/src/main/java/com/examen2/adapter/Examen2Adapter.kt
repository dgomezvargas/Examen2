package com.examen2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examen2.databinding.Examen2FilaBinding
import com.examen2.model.Examen2
import com.examen2.ui.examen2.Examen2FragmentDirections

class Examen2Adapter : RecyclerView.Adapter<Examen2Adapter.Examen2ViewHolder>(){


    private var listaExamen2 = emptyList<Examen2>()

    fun setData(examen2: List<Examen2>){
        this.listaExamen2 = examen2
        notifyDataSetChanged()

    }

    inner class Examen2ViewHolder(private val itemBinding: Examen2FilaBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(examen2: Examen2){
            itemBinding.tvNombre.text = examen2.nombre
            itemBinding.tvCorreo.text = examen2.correo
            itemBinding.tvTelefono.text = examen2.telefono
            itemBinding.tvPrecio.text = examen2.precio.toString()
            itemBinding.tvDistancias.text = examen2.distancias.toString()





            itemBinding.vistaFila.setOnClickListener{
                val accion = Examen2FragmentDirections
                    .actionNavExamen2ToNavUpdateExamen2(examen2)
                itemView.findNavController().navigate(accion)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Examen2ViewHolder {
        var itemBinding = Examen2FilaBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return Examen2ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: Examen2ViewHolder, position: Int) {
        val examen2 = listaExamen2[position]
        holder.bind(examen2)
    }

    override fun getItemCount(): Int {
        return listaExamen2.size
    }



}