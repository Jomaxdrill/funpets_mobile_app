package com.example.funpets_mb.view.ui.fragments

import Data.DBHelper
import Data.Tables
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.funpets_mb.R
import com.example.funpets_mb.databinding.FragmentAdminBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    lateinit var informacionDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        informacionDBHelper = DBHelper(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_admin, container, false)
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.navigate_edition)
        button?.setOnClickListener {
            findNavController().navigate(R.id.adminDetailFragmentDialog, null)
        }

        //Paso 14. Configurar la actualizacion de valores


        val db : SQLiteDatabase = informacionDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + Tables.information.TABLE_NAME, null )
        if (cursor == null){
            Toast.makeText(activity,"Base de datos vacia", Toast.LENGTH_LONG).show()
        }
        if(cursor.moveToFirst()){
            do{
                binding.tvNameAdmin.text = "Nombre: " + cursor.getString(1).toString()
                binding.tvAddressAdmin.text = "Dirección: " + cursor.getString(2).toString()
                binding.tvPhoneAdmin.text = "Telefono: " + cursor.getString(4).toString()
                binding.tvMailAdmin.text = "Correo: " + cursor.getString(3).toString()
            }while (cursor.moveToNext())
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}