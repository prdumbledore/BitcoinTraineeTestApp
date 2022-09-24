package com.eriksargsyan.bitcointraineetestapp.view.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

open class BaseActivity<BINDING : ViewBinding>(
    val inflateFun: (
        inflater: LayoutInflater
    ) -> BINDING
) : AppCompatActivity() {

    private var _binding: BINDING? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateFun(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}