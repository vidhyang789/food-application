package com.examples.foodapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examples.foodapp.DetailsActivity
import com.examples.foodapp.adapter.popularadapter.*
import com.examples.foodapp.databinding.PopularitemsBinding

@SuppressLint("ParcelCreator")
class popularadapter(private val items : List<String>,private val price : List<String>,private val image : List<Int>,private val requireContext : Context) : RecyclerView.Adapter<popularviewholder>(), Parcelable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): popularviewholder {
        return popularviewholder(PopularitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: popularviewholder, position: Int) {
        val Items = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(Items,price,images)

        holder.itemView.setOnClickListener{
            val intent = Intent(requireContext,DetailsActivity::class.java)
            intent.putExtra("MenuItemName", Items)
            intent.putExtra("MenuItemImage", images)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class popularviewholder(private var binding : PopularitemsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.imageView2
        fun bind(items:String, price: String, images: Int) {
            binding.foodNamepopular.text = items
            binding.pricePopular.text = price
            imageView.setImageResource(images)
        }

    }

    override fun describeContents(): Int {
        return 0 // No special contents
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeStringList(items)
        dest.writeStringList(price)
        dest.writeIntArray(image.toIntArray())
    }


}