package com.zeal.paymentassignment.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zeal.paymentassignment.R
import com.zeal.paymentassignment.core.FlowDataObject
import com.zeal.paymentassignment.databinding.FragmentMainMenuBinding


class MainMenuFragment : Fragment() {

    val binding by lazy {
        FragmentMainMenuBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTransaction.setOnClickListener {
            FlowDataObject.getNewInstance()
            FlowDataObject.getInstance().discount = 50f
            findNavController().navigate(R.id.action_mainMenuFragment_to_enterAmountDataFragment)
        }
    }

}
