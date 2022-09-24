package com.eriksargsyan.bitcointraineetestapp.model.domain

import android.graphics.Bitmap
import com.eriksargsyan.bitcointraineetestapp.R


data class Crypto(

    val id: String,

    val abbreviation: String,

    val name: String,

    val imageURL: Bitmap,

    val currentPrice: Double,

    val priceChange: Double,
)