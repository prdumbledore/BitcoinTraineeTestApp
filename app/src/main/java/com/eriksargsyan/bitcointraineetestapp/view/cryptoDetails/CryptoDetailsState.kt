package com.eriksargsyan.bitcointraineetestapp.view.cryptoDetails

import com.eriksargsyan.bitcointraineetestapp.model.domain.CryptoDetails

sealed interface CryptoDetailsState {
    object Loading: CryptoDetailsState
    data class Success(val cryptoDetails: CryptoDetails): CryptoDetailsState
    object Error: CryptoDetailsState
}