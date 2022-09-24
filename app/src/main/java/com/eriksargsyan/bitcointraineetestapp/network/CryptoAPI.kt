package com.eriksargsyan.bitcointraineetestapp.network

import com.eriksargsyan.bitcointraineetestapp.model.network.CryptoDetailsNet
import com.eriksargsyan.bitcointraineetestapp.model.network.CryptoNet
import com.eriksargsyan.bitcointraineetestapp.network.CryptoApiConstants.CURRENCY
import com.eriksargsyan.bitcointraineetestapp.network.CryptoApiConstants.ORDER
import com.eriksargsyan.bitcointraineetestapp.network.CryptoApiConstants.PAGE
import com.eriksargsyan.bitcointraineetestapp.network.CryptoApiConstants.PAGE_QUANTITY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoAPI {

    @GET("coins/markets")
    suspend fun getCryptoList(
        @Query("vs_currency") currency: String = CURRENCY,
        @Query("order") order: String = ORDER,
        @Query("per_page") pageQuantity: Int = PAGE_QUANTITY,
        @Query("page") page: Int = PAGE
    ): List<CryptoNet>

    @GET("coins/{id}")
    suspend fun getCryptoDetails(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") market_data: Boolean = false,
        @Query("community_data") community_data: Boolean = false,
        @Query("developer_data") developer_data: Boolean = false
    ): CryptoDetailsNet
}