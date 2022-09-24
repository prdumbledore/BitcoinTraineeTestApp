package com.eriksargsyan.bitcointraineetestapp.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.eriksargsyan.bitcointraineetestapp.model.domain.Crypto
import com.eriksargsyan.bitcointraineetestapp.model.domain.CryptoDetails
import com.eriksargsyan.bitcointraineetestapp.network.CryptoAPI
import com.eriksargsyan.bitcointraineetestapp.util.IO
import com.eriksargsyan.bitcointraineetestapp.util.NetworkDetailsMapper
import com.eriksargsyan.bitcointraineetestapp.util.NetworkListMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CryptoRepository {
    suspend fun getCryptoList(currency: String): List<Crypto>
    suspend fun getCryptoDetails(id: String): CryptoDetails
    suspend fun hasNetworkAccess(): Boolean
}

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoAPI: CryptoAPI,
    private val cryptoNetworkListMapper: NetworkListMapper,
    private val cryptoNetworkDetailsMapper: NetworkDetailsMapper,
    @IO private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val appContext: Context
) : CryptoRepository {

    override suspend fun getCryptoList(currency: String): List<Crypto> {
        return withContext(ioDispatcher) {
            val cryptoNetList = cryptoAPI.getCryptoList(currency = currency)
            return@withContext cryptoNetworkListMapper.fromEntityToDomainList(cryptoNetList)
        }
    }

    override suspend fun getCryptoDetails(id: String): CryptoDetails {
        return withContext(ioDispatcher) {
            val cryptoNetDetails = cryptoAPI.getCryptoDetails(id)
            return@withContext cryptoNetworkDetailsMapper.fromEntityToDomain(cryptoNetDetails)
        }
    }

    override suspend fun hasNetworkAccess(): Boolean {
        delay(1000)
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}