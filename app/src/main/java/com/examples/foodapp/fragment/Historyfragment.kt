package com.examples.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.foodapp.R
import com.examples.foodapp.adapter.buyagainadapter
import com.examples.foodapp.databinding.FragmentHistoryfragmentBinding


class Historyfragment : Fragment() {

    private lateinit var binding: FragmentHistoryfragmentBinding
    private lateinit var buyagainadapter: buyagainadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryfragmentBinding.inflate(inflater,container,false)
        setuprecyclerview()
        return binding.root
    }

    private fun setuprecyclerview(){
        val BuyAgainFoodName = arrayListOf("Pizza","Burger","Hotdog","chillipaneer","Momos","Cold Coffee")
        val BuyAgainItemprice = arrayListOf("$5","$6","$7","$8","$9","$10")
        val BuyAgainImage = arrayListOf(
            R.drawable.pizza,
            R.drawable.burger,
            R.drawable.hotdog,
            R.drawable.chillipaneer,
            R.drawable.momos,
            R.drawable.coldcoffee)
        buyagainadapter = buyagainadapter(BuyAgainFoodName,BuyAgainItemprice,BuyAgainImage)
        binding.historyRV.adapter = buyagainadapter
        binding.historyRV.layoutManager = LinearLayoutManager(requireContext())
    }
}