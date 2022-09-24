package com.eriksargsyan.bitcointraineetestapp.view.cryptoList

import com.eriksargsyan.bitcointraineetestapp.model.domain.Crypto

sealed interface CryptoListState {
    object Loading: CryptoListState
    data class Success(val cryptoList: List<Crypto>): CryptoListState
    object Error: CryptoListState
}
