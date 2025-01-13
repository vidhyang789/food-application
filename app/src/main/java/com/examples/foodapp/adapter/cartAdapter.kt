package com.examples.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examples.foodapp.databinding.CartitemsBinding

class cartAdapter(private val cartItems : MutableList<String>,private val cartItemPrices : MutableList<String>,private var cartItemImage : MutableList<Int>) : RecyclerView.Adapter<cartAdapter.CartViewHolder>(){

private val itemQuantities = IntArray(cartItems.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    inner class CartViewHolder(private val binding : CartitemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartfoodname.text = cartItems[position]
                pricePopular.text = cartItemPrices[position]
                cartImage.setImageResource(cartItemImage[position])
                textView14.text = quantity.toString()

                AddtoCartpopular.setOnClickListener {
                    decreaseQuantity(position)
                }
                AddtoCartpopular2.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    val itemposition = adapterPosition
                    if (itemposition != RecyclerView.NO_POSITION) {
                        deleteitem(itemposition)
                    }
                }

            }
        }
        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.textView14.text = itemQuantities[position].toString()
            }
            else{
                val itemposition = adapterPosition
                if (itemposition != RecyclerView.NO_POSITION) {
                    deleteitem(itemposition)
                }
            }
        }
        private fun increaseQuantity(position: Int) {
            itemQuantities[position]++
            binding.textView14.text = itemQuantities[position].toString()
        }
        private fun deleteitem(position: Int) {
            cartItems.removeAt(position)
            cartItemPrices.removeAt(position)
            cartItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
        }

    }

}