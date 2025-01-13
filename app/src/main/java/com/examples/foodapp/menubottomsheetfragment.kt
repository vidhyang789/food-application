package com.examples.foodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.foodapp.R
import com.examples.foodapp.databinding.FragmentMenubottomsheetfragmentBinding
import com.examples.foodapp.adapter.menuadapter
import com.examples.foodapp.datamodel.menumodel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class menubottomsheetfragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenubottomsheetfragmentBinding
    private lateinit var menuitems:MutableList<menumodel>
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenubottomsheetfragmentBinding.inflate(inflater,container,false)

        binding.imageButton.setOnClickListener {
            dismiss()
        }

        retrivedata()

        return binding.root
    }

    private fun retrivedata() {
        database = FirebaseDatabase.getInstance()
        val foodref = database.reference.child("menu")
        menuitems = mutableListOf()

        foodref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshot in snapshot.children) {
                    val menuitem = snapshot.getValue(menumodel::class.java)
                    menuitems.add(menuitem!!);
                }
                setadapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun setadapter() {
        val adapter = menuadapter(menuitems,requireContext())
        binding.menubottomfragmentRV.layoutManager = LinearLayoutManager(requireContext())
        binding.menubottomfragmentRV.adapter = adapter
    }

}