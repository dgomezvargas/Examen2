package com.examen2.ui.examen2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.examen2.R
import com.examen2.databinding.FragmentAddExamen2Binding
import com.examen2.model.Examen2
import com.examen2.viewmodel.Examen2ViewModel

class AddExamen2Fragment : Fragment() {
    private lateinit var examen2ViewModel: Examen2ViewModel
    private var _binding: FragmentAddExamen2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        examen2ViewModel =
            ViewModelProvider(this).get(Examen2ViewModel::class.java)

        _binding = FragmentAddExamen2Binding.inflate(inflater, container, false)
        binding.btAgregar.setOnClickListener{
            agregarExamen2()

        }

        return binding.root
    }

    private fun agregarExamen2() {
        val nombre=binding.etNombre.text.toString()
        if (nombre.isNotEmpty()){
            val correo=binding.etCorreo.text.toString()
            val telefono=binding.etTelefono.text.toString()
            val precio=binding.etPrecio.text.toString().toInt()
            val distancias=binding.etDistancias.text.toString().toDouble()
            val examen2= Examen2(0 ,nombre, correo,telefono,precio,distancias)
            examen2ViewModel.addExamen2(examen2)
            Toast.makeText(requireContext(),
                getString(R.string.msg_examen2_add),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addExamen2Fragment_to_nav_examen2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}