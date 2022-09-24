package com.eriksargsyan.bitcointraineetestapp.util

import android.graphics.BitmapFactory
import com.eriksargsyan.bitcointraineetestapp.model.network.CryptoNet
import com.eriksargsyan.bitcointraineetestapp.model.domain.Crypto
import java.net.URL
import javax.inject.Inject

class NetworkListMapper @Inject constructor() : EntityToDomainMapper<CryptoNet, Crypto> {

    override fun fromEntityToDomain(entity: CryptoNet) = Crypto(
        id = entity.id,
        abbreviation = entity.abbreviation.uppercase(),
        name = entity.name,
        imageURL = BitmapFactory.decodeStream(
            URL(entity.imageURL).openConnection().getInputStream()
        ),
        currentPrice = entity.currentPrice,
        priceChange = entity.priceChange
    )

    override fun fromDomainToEntity(domain: Crypto) = CryptoNet(
        id = domain.id,
        abbreviation = domain.abbreviation.lowercase(),
        name = domain.name,
        imageURL = domain.imageURL.toString(),
        currentPrice = domain.currentPrice,
        priceChange = domain.priceChange
    )

    override fun fromEntityToDomainList(entity: List<CryptoNet>): List<Crypto> =
        entity.map { fromEntityToDomain(it) }

    override fun fromDomainToEntityList(domain: List<Crypto>): List<CryptoNet> =
        domain.map { fromDomainToEntity(it) }
}