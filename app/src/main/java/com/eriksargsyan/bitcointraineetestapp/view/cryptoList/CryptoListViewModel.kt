package com.eriksargsyan.bitcointraineetestapp.view.cryptoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.bitcointraineetestapp.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): ViewModel() {

    private val _state = MutableStateFlow<CryptoListState>(CryptoListState.Loading)
    val state: StateFlow<CryptoListState> = _state

    fun fetchCryptoList(currency: String) {
        viewModelScope.launch {
            _state.value = if (cryptoRepository.hasNetworkAccess()) {
                try {
                    CryptoListState.Success(cryptoRepository.getCryptoList(currency = currency))
                } catch (e: IllegalStateException) {
                    CryptoListState.Error
                }
            } else {
                CryptoListState.Error
            }
        }
    }

    fun setLoadingState() {
        _state.value = CryptoListState.Loading
    }
}