package com.examples.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.examples.foodapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding
    private  var foddname : String? = null
    private  var foddprice : String? = null
    private  var foddimage : String? = null
    private  var fodddescription : String? = null
    private  var foddingridients : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foddname = intent.getStringExtra("ItemName");
        foddprice = intent.getStringExtra("itemPrice");
        fodddescription = intent.getStringExtra("ItemDescription");
        foddingridients = intent.getStringExtra("ItemIngredients");
        foddimage = intent.getStringExtra("ItemImage");

        binding.detailfoodname.text = foddname;
        binding.fooddescription.text = fodddescription;
        binding.fooIngredients.text = foddingridients;
        Glide.with(this).load(foddimage).into(binding.detailimage);


        binding.imageButton4.setOnClickListener {
            finish()
        }
    }
}