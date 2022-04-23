package com.examen2.ui.examen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.examen2.R
import com.examen2.adapter.Examen2Adapter
import com.examen2.databinding.FragmentExamen2Binding
import com.examen2.viewmodel.Examen2ViewModel
import androidx.recyclerview.widget.LinearLayoutManager

class Examen2Fragment : Fragment() {

    private lateinit var examen2ViewModel: Examen2ViewModel
    private var _binding: FragmentExamen2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val examen2ViewModel =
            ViewModelProvider(this).get(Examen2ViewModel::class.java)

        _binding = FragmentExamen2Binding.inflate(inflater, container, false)
        binding.fbAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_examen2_to_addExamen2Fragment)
        }

        val examen2Adapter = Examen2Adapter()
        val reciclador = binding.reciclador

        reciclador.adapter = examen2Adapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        examen2ViewModel.getAllData.observe(viewLifecycleOwner){ examen2->
            examen2Adapter.setData(examen2)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}