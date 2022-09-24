package com.eriksargsyan.bitcointraineetestapp.view.cryptoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eriksargsyan.bitcointraineetestapp.R
import com.eriksargsyan.bitcointraineetestapp.databinding.ItemRecyclerBitcoinBinding
import com.eriksargsyan.bitcointraineetestapp.model.domain.Crypto
import com.eriksargsyan.bitcointraineetestapp.util.StringTransformer.currentPriceToStr
import com.eriksargsyan.bitcointraineetestapp.util.StringTransformer.priceChangeToStr

class CryptoListAdapter(
    private val onCardClickListener: (Crypto) -> Unit
): RecyclerView.Adapter<CryptoListAdapter.CryptoListViewHolder>() {

    private val list: MutableList<Crypto> = mutableListOf()
    private var currency: String = "usd"

    fun submitList(newList: List<Crypto>, currency: String) {
        list.clear()
        list.addAll(newList)
        this.currency = currency
        notifyDataSetChanged()
    }

    inner class CryptoListViewHolder(
        private val binding: ItemRecyclerBitcoinBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listItem: Crypto) {
            with(binding) {

                bitcoinImage.setImageBitmap(listItem.imageURL)
                cryptoName.text = listItem.name
                cryptoAbbr.text = listItem.abbreviation
                cryptoPriceCurrency.text = if (currency == "usd") "$" else "€"
                cryptoPrice.text = currentPriceToStr(listItem.currentPrice)
                cryptoGrowth.apply {
                    text = priceChangeToStr(listItem.priceChange)
                    setTextColor(
                        if (listItem.priceChange >= 0.0) {
                            resources.getColor(R.color.growth_up)
                        } else {
                            resources.getColor(R.color.growth_down)
                        }
                    )
                }

                cryptoCard.setOnClickListener {
                    onCardClickListener.invoke(listItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        val itemViewBinding = ItemRecyclerBitcoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoListViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}