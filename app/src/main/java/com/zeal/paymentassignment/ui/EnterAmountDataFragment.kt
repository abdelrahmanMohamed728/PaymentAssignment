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
import com.abdelrahmanmohamed728.loyaltypointsapp.IDiscountCallback
import com.abdelrahmanmohamed728.loyaltypointsapp.ILoyaltyInterface
import com.zeal.paymentassignment.R
import com.zeal.paymentassignment.core.FlowDataObject
import com.zeal.paymentassignment.databinding.FragmentEnterAmount2Binding

class EnterAmountDataFragment : Fragment() {
    val binding by lazy {
        FragmentEnterAmount2Binding.inflate(layoutInflater)
    }

    private var mService: ILoyaltyInterface? = null
    private var isBound = false

    private val mConnection = object : ServiceConnection {
        override fun onNullBinding(name: ComponentName?) {
            super.onNullBinding(name)
        }

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mService = ILoyaltyInterface.Stub.asInterface(service)
            isBound = true
            Toast.makeText(activity, "Connected", Toast.LENGTH_LONG).show()
            initDiscountApplication()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            mService = null
            isBound = false
        }
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
        binding.btnConfirm.setOnClickListener {
            val amount = binding.tvEnterAmount.text.toString()
            if (amount.isNotBlank()) {
                try {
                    val amountF = amount.toFloat()
                    if (amountF == 0.0f)
                        Toast.makeText(context, "cant be zero", Toast.LENGTH_SHORT).show()
                    else {
                        FlowDataObject.getInstance().amount = amountF
                        initIntent()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "please add valid number", Toast.LENGTH_SHORT).show()
                }
            } else
                Toast.makeText(context, "cant be empty", Toast.LENGTH_SHORT).show()

        }


    }

    private fun initIntent() {
        val intent = Intent("aidlexample")
        intent.setPackage("com.abdelrahmanmohamed728.loyaltypointsapp")
        context?.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    private fun initDiscountApplication() {
        mService?.getPriceAfterDiscount(FlowDataObject.getInstance().amount,
            FlowDataObject.getInstance().discount, object : IDiscountCallback.Stub() {
                override fun onPriceCalculated(remainingPrice: Float) {
                    FlowDataObject.getInstance().amount = remainingPrice
                    findNavController().navigate(R.id.action_enterAmountDataFragment_to_swipeCardFragment)
                }

                override fun onNoRemainingAmount() {
                    FlowDataObject.getInstance().amount = 0f
                    findNavController().navigate(R.id.action_enterAmountDataFragment_to_printReceiptFragment)
                }

            })
    }
}