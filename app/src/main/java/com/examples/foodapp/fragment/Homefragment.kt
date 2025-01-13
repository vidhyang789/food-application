package com.examples.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.examples.foodapp.R
import com.examples.foodapp.adapter.menuadapter
import com.examples.foodapp.adapter.popularadapter
import com.examples.foodapp.databinding.FragmentHomefragmentBinding
import com.examples.foodapp.datamodel.menumodel
import com.examples.foodapp.menubottomsheetfragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Suppress("UNREACHABLE_CODE")
class Homefragment : Fragment() {

    private lateinit var binding: FragmentHomefragmentBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuitems : MutableList<menumodel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomefragmentBinding.inflate(inflater, container, false)
        binding.textView12.setOnClickListener {
            val bottomSheetDialog = menubottomsheetfragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        retriveanddisplaymenuitems()

        return binding.root

    }

    private fun retriveanddisplaymenuitems() {
        database = FirebaseDatabase.getInstance()
        val myref = database.getReference("menu")
        menuitems = mutableListOf()

        myref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshot in snapshot.children) {
                    val menuitem = snapshot.getValue(menumodel::class.java)
                    menuitems.add(menuitem!!);
                }
                setadapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun setadapter() {
        val index = menuitems.indices.toList().shuffled()
        val numminitemtoshow = 6
        val subsetmenuitems = index.take(numminitemtoshow).map { menuitems[it] }

        setPopularAdapter(subsetmenuitems)
    }

    private fun setPopularAdapter(subsetmenuitems: List<menumodel>) {
        val adapter = menuadapter(subsetmenuitems,requireContext())
        binding.popularitemsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.popularitemsRV.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        @Suppress("UNREACHABLE_CODE")
        val imagelist = ArrayList<SlideModel>()
        imagelist.add(SlideModel(R.drawable.banner1))
        imagelist.add(SlideModel(R.drawable.banner2))
        imagelist.add(SlideModel(R.drawable.banner3))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imagelist)
        imageSlider.setImageList(imagelist, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPositionj = imagelist[position]
                val itemMessage = "Selected Item $position"
                Toast.makeText(requireContext(),itemMessage,Toast.LENGTH_SHORT).show()
            }
        })
    }


}