package com.examples.myfoodapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.examples.foodapp.databinding.ActivityChooselocationBinding

class chooselocation : AppCompatActivity() {

    private val binding : ActivityChooselocationBinding by lazy{
        ActivityChooselocationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationlist : Array<String> = arrayOf("Jaipur","Ajmer","Kolkata","Makrana","pune","Bombay")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationlist)
        val autoCompleteTextView : AutoCompleteTextView = binding.listoflocation
        autoCompleteTextView.setAdapter(adapter)
    }
}