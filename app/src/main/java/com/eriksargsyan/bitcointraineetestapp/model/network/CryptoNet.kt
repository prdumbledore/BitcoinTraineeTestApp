package com.eriksargsyan.bitcointraineetestapp.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptoNet(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("symbol")
    val abbreviation: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("image")
    val imageURL: String,

    @Expose
    @SerializedName("current_price")
    val currentPrice: Double,

    @Expose
    @SerializedName("price_change_percentage_24h")
    val priceChange: Double,

)
