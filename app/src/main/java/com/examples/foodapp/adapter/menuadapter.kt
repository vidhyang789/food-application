package com.examples.foodapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.foodapp.DetailsActivity
import com.examples.foodapp.adapter.menuadapter.MenuViewHolder
import com.examples.foodapp.databinding.MenuitemBinding
import com.examples.foodapp.datamodel.menumodel

class menuadapter(
    private val menuitems: List<menumodel>,
    private val requireContext: Context
) : RecyclerView.Adapter<MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return menuitems.size
    }

    inner class MenuViewHolder(private val binding: MenuitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    topenDetails(position)
                }
            }
        }

        fun bind(position: Int) {
            binding.apply {
                menufoodname.text = menuitems[position].foodName
                menuprice.text = menuitems[position].foodPrice
                var uri = Uri.parse(menuitems[position].foodImage)
                Glide.with(requireContext).load(uri).into(menuimage)
            }
        }

    }

    private fun topenDetails(position: Int) {
        val menuitem = menuitems[position]

        //detail activity open
        val intent = Intent(requireContext, DetailsActivity::class.java).apply {
            putExtra("ItemName", menuitem.foodName)
            putExtra("itemPrice", menuitem.foodPrice)
            putExtra("ItemDescription", menuitem.foodDescription)
            putExtra("ItemImage", menuitem.foodImage)
            putExtra("ItemIngredients", menuitem.foodIngredients)
        }
        requireContext.startActivity(intent)
    }

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

}


