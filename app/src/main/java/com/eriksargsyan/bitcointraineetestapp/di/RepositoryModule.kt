package com.eriksargsyan.bitcointraineetestapp.di

import com.eriksargsyan.bitcointraineetestapp.repository.CryptoRepository
import com.eriksargsyan.bitcointraineetestapp.repository.CryptoRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Binds

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCryptoRepository(impl: CryptoRepositoryImpl): CryptoRepository
}