package com.eriksargsyan.bitcointraineetestapp.view.cryptoList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eriksargsyan.bitcointraineetestapp.R
import com.eriksargsyan.bitcointraineetestapp.databinding.CryptoListFragmentBinding
import com.eriksargsyan.bitcointraineetestapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoListFragment : BaseFragment<CryptoListFragmentBinding>(
    { inflater, container -> CryptoListFragmentBinding.inflate(inflater, container, false) }
) {

    private val viewModel: CryptoListViewModel by activityViewModels()


    private val currency: String
        get() = if (binding.chipGroup.checkedChipId == binding.usdChip.id)
            binding.usdChip.text.toString().lowercase()
        else
            binding.eurChip.text.toString().lowercase()


    private val cryptoAdapter: CryptoListAdapter by lazy {
        CryptoListAdapter { crypto ->
            findNavController().navigate(
                CryptoListFragmentDirections.actionCryptoListFragmentToCryptoDetailsFragment(
                    cryptoId = crypto.id,
                    cryptoName = crypto.name
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            cryptoListFragment.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = cryptoAdapter
            }
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = true
                viewModel.setLoadingState()
                viewModel.fetchCryptoList(currency)
            }
            chipGroup.setOnCheckedChangeListener { _, _ ->
                viewModel.setLoadingState()
                viewModel.fetchCryptoList(currency)
            }

            loadingErrorList.tryAgainButton.setOnClickListener {
                viewModel.setLoadingState()
                viewModel.fetchCryptoList(currency)
            }

            viewModel.fetchCryptoList(currency)

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.state.collect { cryptoState ->
                    when (cryptoState) {
                        is CryptoListState.Loading -> {
                            loadingErrorList.cryptoProgressBar.visibility = View.VISIBLE
                            cryptoListFragment.visibility = View.GONE
                            loadingErrorList.errorLayout.visibility = View.GONE
                        }
                        is CryptoListState.Success -> {
                            loadingErrorList.cryptoProgressBar.visibility = View.GONE
                            cryptoListFragment.visibility = View.VISIBLE
                            loadingErrorList.errorLayout.visibility = View.GONE
                            swipeRefreshLayout.isRefreshing = false
                            cryptoAdapter.submitList(cryptoState.cryptoList, currency)
                        }
                        is CryptoListState.Error -> {
                            loadingErrorList.cryptoProgressBar.visibility = View.GONE
                            cryptoListFragment.visibility = View.GONE
                            loadingErrorList.errorLayout.visibility = View.VISIBLE
                            if (swipeRefreshLayout.isRefreshing) showMessage(R.string.no_net_connection)
                            swipeRefreshLayout.isRefreshing = false
                        }
                    }
                }
            }
        }

    }


}