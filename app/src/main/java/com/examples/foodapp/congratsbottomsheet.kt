package com.examples.foodapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.examples.foodapp.databinding.FragmentCongratsbottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class congratsbottomsheet :BottomSheetDialogFragment() {

    private lateinit var binding : FragmentCongratsbottomsheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCongratsbottomsheetBinding.inflate(layoutInflater,container,false)
        binding.gohome.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}