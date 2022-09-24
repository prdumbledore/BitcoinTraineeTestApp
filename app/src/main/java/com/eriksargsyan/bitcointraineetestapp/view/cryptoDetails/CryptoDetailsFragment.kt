package com.eriksargsyan.bitcointraineetestapp.view.cryptoDetails

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.eriksargsyan.bitcointraineetestapp.databinding.CryptoDetailsFragmentBinding
import com.eriksargsyan.bitcointraineetestapp.model.domain.CryptoDetails
import com.eriksargsyan.bitcointraineetestapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoDetailsFragment : BaseFragment<CryptoDetailsFragmentBinding>(
    { inflate, container -> CryptoDetailsFragmentBinding.inflate(inflate, container, false) }
) {

    private val args: CryptoDetailsFragmentArgs by navArgs()
    private var cryptoId: String = ""

    private val viewModel: CryptoDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        cryptoId = args.cryptoId
        (activity as AppCompatActivity).supportActionBar?.title = args.cryptoName

        with(binding) {

            loadingErrorDetails.tryAgainButton.setOnClickListener {
                viewModel.setLoadingState()
                viewModel.fetchCryptoDetails(cryptoId)
            }

            viewModel.setLoadingState()
            viewModel.fetchCryptoDetails(cryptoId)

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.state.collect { cryptoDetailsState ->
                    when (cryptoDetailsState) {
                        is CryptoDetailsState.Loading -> {
                            loadingErrorDetails.cryptoProgressBar.visibility = View.VISIBLE
                            btcLargeImg.visibility = View.GONE
                            detailTextLinearLayout.visibility = View.GONE
                            loadingErrorDetails.errorLayout.visibility = View.GONE
                        }
                        is CryptoDetailsState.Success -> {
                            loadingErrorDetails.cryptoProgressBar.visibility = View.GONE
                            btcLargeImg.visibility = View.VISIBLE
                            detailTextLinearLayout.visibility = View.VISIBLE
                            loadingErrorDetails.errorLayout.visibility = View.GONE
                            fillField(cryptoDetailsState.cryptoDetails)
                        }
                        is CryptoDetailsState.Error -> {
                            loadingErrorDetails.cryptoProgressBar.visibility = View.GONE
                            btcLargeImg.visibility = View.GONE
                            detailTextLinearLayout.visibility = View.GONE
                            loadingErrorDetails.errorLayout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun fillField(cryptoDetails: CryptoDetails) {
        binding.apply {
            btcLargeImg.setImageBitmap(cryptoDetails.image)
            descriptionText.text = Html.fromHtml(cryptoDetails.description)
            descriptionText.movementMethod = LinkMovementMethod.getInstance()
            categoriesText.text = cryptoDetails.categories.joinToString(separator = ", ")
        }
    }

}