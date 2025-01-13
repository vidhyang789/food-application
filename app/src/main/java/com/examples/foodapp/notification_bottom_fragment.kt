package com.examples.foodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.foodapp.R
import com.examples.foodapp.databinding.FragmentNotificationBottomFragmentBinding
import com.examples.foodapp.adapter.NotificationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class notification_bottom_fragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNotificationBottomFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBottomFragmentBinding.inflate(inflater,container,false)
        val notification = listOf("Your order has been cancelled","order has been taken by the driver","Congrats your oder has been placed")
        val notificationimage = listOf(R.drawable.sad,R.drawable.delivery,R.drawable.check)
        val adapter = NotificationAdapter(ArrayList(notification),ArrayList(notificationimage))
        binding.notificationRV.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRV.adapter = adapter
        return binding.root
    }

}