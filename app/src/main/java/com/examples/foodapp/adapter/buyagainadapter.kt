package com.examples.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examples.foodapp.databinding.BuyagainitemBinding

class buyagainadapter(private val BuyAgainFoodName: ArrayList<String>,
                      private val BuyAgainItemprice: ArrayList<String>,
                      private val BuyAgainImage:  ArrayList<Int>)
    : RecyclerView.Adapter<buyagainadapter.BuyAgainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding = BuyagainitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BuyAgainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(BuyAgainFoodName[position],BuyAgainItemprice[position],BuyAgainImage[position])
    }


    override fun getItemCount(): Int {
        return BuyAgainFoodName.size
    }


    class BuyAgainViewHolder(private val binding: BuyagainitemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(foodname: String, foodprice: String, foodimage: Int) {
            binding.buyagainfoodname.text = foodname
            binding.buyagainfoodprice.text = foodprice
            binding.buyagainimg.setImageResource(foodimage)
        }

    }
}