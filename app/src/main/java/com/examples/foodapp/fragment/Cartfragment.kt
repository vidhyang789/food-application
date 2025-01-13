package com.examples.foodapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.foodapp.R
import com.examples.foodapp.adapter.cartAdapter
import com.examples.foodapp.databinding.FragmentCartfragmentBinding
import com.examples.foodapp.orderplacing
import java.util.ArrayList

class Cartfragment : Fragment() {

    private lateinit var binding : FragmentCartfragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartfragmentBinding.inflate(inflater,container,false)

        val cartFoodname = listOf("Pizza","Burger","Hotdog","chillipaneer","Momos","Cold Coffee")
        val cartItemPrice = listOf("$5","$6","$7","$8","$9","$10")
        val cartImage = listOf(
            R.drawable.pizza,
            R.drawable.burger,
            R.drawable.hotdog,
            R.drawable.chillipaneer,
            R.drawable.momos,
            R.drawable.coldcoffee
        )
        val adapter = cartAdapter(ArrayList(cartFoodname), ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.cartRCview.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRCview.adapter = adapter
        binding.proceedbtn.setOnClickListener{
            val intent = Intent(requireContext(), orderplacing::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}