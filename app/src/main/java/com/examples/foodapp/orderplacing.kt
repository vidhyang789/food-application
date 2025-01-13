package com.examples.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examples.foodapp.databinding.ActivityOrderplacingBinding

class orderplacing : AppCompatActivity() {

    lateinit var binding : ActivityOrderplacingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderplacingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button3.setOnClickListener {
            val bottomSheetDialog = congratsbottomsheet()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }

    }
}