package com.example.practica10mendozareyesangelemanuel.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.practica10mendozareyesangelemanuel.Cliente
import com.example.practica10mendozareyesangelemanuel.R
import com.example.practica10mendozareyesangelemanuel.databinding.FragmentNotificationsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var calendario: CalendarView

    var cliente = Cliente()

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val view: View = binding.root
        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        cliente = Cliente()
        name = view.findViewById(R.id.editNombre)
        email = view.findViewById(R.id.editCorreo)
        calendario = view.findViewById(R.id.cvCalendario)

        val iniciarFecha = Calendar.getInstance()
        val year = iniciarFecha.get(Calendar.YEAR)
        val month = iniciarFecha.get(Calendar.MONTH)
        val dayOfMonth = iniciarFecha.get(Calendar.DAY_OF_MONTH)
        val date = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        cliente.fecha = date

        val btnAgregar = view?.findViewById<FloatingActionButton>(R.id.btnAgregar)
        btnAgregar?.setOnClickListener { v ->
            agregar()
        }
        return view
    }

    private fun agregar() {
        if(name.text.isNotBlank() && name.text.isNotEmpty() &&
            email.text.isNotBlank() && email.text.isNotEmpty()){
            cliente.nombre = name.text.toString()
            cliente.correo = email.text.toString()
            calendario.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val date = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                cliente.fecha = date
            }
            Toast.makeText(requireContext(), "Nombre: ${cliente.nombre}", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), "Correo: ${cliente.correo}", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), "Fecha: ${cliente.fecha}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}