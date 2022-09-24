package com.eriksargsyan.bitcointraineetestapp.model.domain

import android.graphics.Bitmap

data class CryptoDetails(

    val id: String,

    val name: String,

    val categories: List<String>,

    val description: String,

    val image: Bitmap
)
