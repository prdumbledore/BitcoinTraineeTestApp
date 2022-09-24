package com.eriksargsyan.bitcointraineetestapp.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CryptoDetailsNet(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("categories")
    val categories: List<String>,

    @Expose
    @SerializedName("description")
    val description: DescriptionNet,

    @Expose
    @SerializedName("image")
    val image: ImageNet
)

data class DescriptionNet(
    @Expose
    @SerializedName("en")
    val text: String
)

data class ImageNet(
    @Expose
    @SerializedName("large")
    val largeImg: String
)