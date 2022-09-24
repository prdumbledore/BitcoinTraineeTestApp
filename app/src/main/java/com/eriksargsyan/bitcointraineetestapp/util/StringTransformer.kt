package com.eriksargsyan.bitcointraineetestapp.util

import java.lang.StringBuilder
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
object StringTransformer {

    fun currentPriceToStr(currentPrice: Double): String {
        val strList = currentPrice.toString().split(".")
        val sb = StringBuilder()
        var str = strList[0].reversed()
        while (str.length > 3) {
            sb.append("${str.take(3)},")
            str = str.drop(3)
        }
        sb.append(str)
        sb.reverse()

        return sb.append(".${strList[1].take(3)}").toString()
    }

    fun priceChangeToStr(priceChange: Double): String {
        val sb = StringBuilder()
        if (priceChange > 0) sb.append("+ ") else sb.append("- ")
        sb.append(String.format("%.2f", abs(priceChange))).append("%")
        return sb.toString()
    }
}

