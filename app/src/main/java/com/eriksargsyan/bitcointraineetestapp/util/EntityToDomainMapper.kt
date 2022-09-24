package com.eriksargsyan.bitcointraineetestapp.util

interface EntityToDomainMapper<Entity, Domain> {

    fun fromEntityToDomain(entity: Entity): Domain

    fun fromDomainToEntity(domain: Domain): Entity

    fun fromEntityToDomainList(entity: List<Entity>): List<Domain>

    fun fromDomainToEntityList(domain: List<Domain>): List<Entity>
}