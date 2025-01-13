package com.examples.foodapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.foodapp.R
import com.examples.foodapp.adapter.menuadapter
import com.examples.foodapp.databinding.FragmentSearchfragmentBinding

class Searchfragment : Fragment() {

    private lateinit var binding: FragmentSearchfragmentBinding
    private lateinit var  adapter : menuadapter
    private val originalMenuFoodName = listOf("Pizza","Burger","Hotdog","chillipaneer","Momos","Cold Coffee")
    val OriginalMenuItemprice = listOf("$5","$6","$7","$8","$9","$10")
    val OriginalMenuImage = listOf(
        R.drawable.pizza,
        R.drawable.burger,
        R.drawable.hotdog,
        R.drawable.chillipaneer,
        R.drawable.momos,
        R.drawable.coldcoffee
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuItemprice = mutableListOf<String>()
    private val filterMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchfragmentBinding.inflate(inflater,container,false)
//        adapter = menuadapter(filterMenuFoodName,filterMenuItemprice,filterMenuImage,requireContext())
        binding.menubottomfragmentRV.layoutManager = LinearLayoutManager(requireContext())
        binding.menubottomfragmentRV.adapter = adapter

        setupSearchView()

        showAllMenuItems()

        return binding.root
    }

    private fun showAllMenuItems() {
        filterMenuFoodName.clear()
        filterMenuItemprice.clear()
        filterMenuImage.clear()


        filterMenuFoodName.addAll(originalMenuFoodName)
        filterMenuItemprice.addAll(OriginalMenuItemprice)
        filterMenuImage.addAll(OriginalMenuImage)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                filterMenuItems(p0)
                return true
            }

        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun filterMenuItems(query: String) {
        filterMenuFoodName.clear()
        filterMenuItemprice.clear()
        filterMenuImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query , ignoreCase = true)) {
                filterMenuFoodName.add(foodName)
                filterMenuItemprice.add(OriginalMenuItemprice[index])
                filterMenuImage.add(OriginalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

}