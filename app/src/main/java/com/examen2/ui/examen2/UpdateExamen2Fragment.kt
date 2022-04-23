package com.examen2.ui.examen2

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.examen2.R
import com.examen2.databinding.FragmentAddExamen2Binding
import com.examen2.databinding.FragmentUpdateExamen2Binding
import com.examen2.model.Examen2
import com.examen2.viewmodel.Examen2ViewModel


class UpdateExamen2Fragment : Fragment() {
    private lateinit var  examen2ViewModel: Examen2ViewModel
    private var _binding: FragmentUpdateExamen2Binding? = null
    private val binding get() = _binding!!


    private val args by navArgs<UpdateExamen2FragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        examen2ViewModel = ViewModelProvider(this).get(Examen2ViewModel::class.java)

        _binding = FragmentUpdateExamen2Binding.inflate(inflater, container, false)

        binding.etNombre.setText(args.examen2.nombre)
        binding.etTelefono.setText(args.examen2.telefono)
        binding.etCorreo.setText(args.examen2.correo)
        binding.etPrecio.setText(args.examen2.precio.toString())
        binding.etDistancias.setText(args.examen2.distancias.toString())
        binding.btPhone.setOnClickListener{ hacerLlamada() }



        binding.btUpdateExamen2.setOnClickListener {
            actualizarExamen2()
        }
        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete_menu){
            deleteExamen2()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun actualizarExamen2() {
        val nombre= binding.etNombre.text.toString()
        if (nombre.isNotEmpty()){
            val correo=binding.etCorreo.text.toString()
            val telefono=binding.etTelefono.text.toString()
            val precio=binding.etPrecio.text.toString().toInt()
            val distancias=binding.etDistancias.text.toString().toDouble()
            val examen2= Examen2(0 ,nombre, correo,telefono,precio,distancias)
            examen2ViewModel.updateExamen2(examen2)
            Toast.makeText(requireContext(),
                getString(R.string.msg_examen2_update),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navUpdateExamen2_to_nav_examen2)
        }

    }


    private fun deleteExamen2(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_seguroBorrar)+" ${args.examen2.nombre}?")
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setPositiveButton(getString(R.string.si)){_,_ ->
            examen2ViewModel.deleteExamen2(args.examen2)
            findNavController().navigate(R.id.action_navUpdateExamen2_to_nav_examen2)

        }
        builder.create().show()

    }


    private fun hacerLlamada() {
        val telefono = binding.etTelefono.text.toString()
        if (telefono.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$telefono")


            if (requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {

                requireActivity().requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),105)

            } else {
                requireActivity().startActivity(intent)
            }

        } else {
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}