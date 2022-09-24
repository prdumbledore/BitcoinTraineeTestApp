package com.eriksargsyan.bitcointraineetestapp.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Singleton
object Dispatchers {
    @IO
    val IO = Dispatchers.IO

    @Main
    val Main = Dispatchers.Main

    @Default
    val Default = Dispatchers.Default
}


annotation class IO

annotation class Default

annotation class Main