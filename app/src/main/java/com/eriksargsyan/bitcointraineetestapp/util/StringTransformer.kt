package com.eriksargsyan.bitcointraineetestapp.util

import java.lang.StringBuilder
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
object StringTransformer {

    fun currentPriceToStr(currentPrice: Double): String {
        return if (currentPrice.toInt() >= 1) String.format("%,.2f", currentPrice)
        else String.format("%,.5f", currentPrice)
    }

    fun priceChangeToStr(priceChange: Double): String {
        val sb = StringBuilder()
        if (priceChange > 0) sb.append("+ ") else sb.append("- ")
        sb.append(String.format("%.2f", abs(priceChange))).append("%")
        return sb.toString()
    }
}

