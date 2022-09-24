package com.eriksargsyan.bitcointraineetestapp.util


import android.graphics.BitmapFactory
import com.eriksargsyan.bitcointraineetestapp.model.domain.CryptoDetails
import com.eriksargsyan.bitcointraineetestapp.model.network.CryptoDetailsNet
import com.eriksargsyan.bitcointraineetestapp.model.network.DescriptionNet
import com.eriksargsyan.bitcointraineetestapp.model.network.ImageNet
import java.net.URL
import javax.inject.Inject

class NetworkDetailsMapper @Inject constructor(): EntityToDomainMapper<CryptoDetailsNet, CryptoDetails>  {

    override fun fromEntityToDomain(entity: CryptoDetailsNet) = CryptoDetails (
        id = entity.id,
        name = entity.name,
        categories = entity.categories,
        description = entity.description.text,
        image = BitmapFactory.decodeStream(
            URL(entity.image.largeImg).openConnection().getInputStream()
        )
    )

    override fun fromDomainToEntity(domain: CryptoDetails) = CryptoDetailsNet (
        id = domain.id,
        name = domain.name,
        categories = domain.categories,
        description = DescriptionNet(domain.description),
        image = ImageNet(domain.image.toString())
    )

    override fun fromEntityToDomainList(entity: List<CryptoDetailsNet>): List<CryptoDetails> =
        entity.map { fromEntityToDomain(it) }

    override fun fromDomainToEntityList(domain: List<CryptoDetails>): List<CryptoDetailsNet> =
        domain.map { fromDomainToEntity(it) }
}