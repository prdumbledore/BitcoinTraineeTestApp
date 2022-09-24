package com.eriksargsyan.bitcointraineetestapp.view.cryptoDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.bitcointraineetestapp.repository.CryptoRepository
import com.eriksargsyan.bitcointraineetestapp.view.cryptoList.CryptoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): ViewModel() {

    private val _state = MutableStateFlow<CryptoDetailsState>(CryptoDetailsState.Loading)
    val state: StateFlow<CryptoDetailsState> = _state

    fun fetchCryptoDetails(id: String) {
        viewModelScope.launch {
            _state.value = if (cryptoRepository.hasNetworkAccess()) {
                try {
                    CryptoDetailsState.Success(cryptoRepository.getCryptoDetails(id))
                } catch (e: IllegalStateException) {
                    CryptoDetailsState.Error
                }
            } else CryptoDetailsState.Error
        }
    }

    fun setLoadingState() {
        _state.value = CryptoDetailsState.Loading
    }
}