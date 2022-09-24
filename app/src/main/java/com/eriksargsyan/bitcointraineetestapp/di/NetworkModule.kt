package com.eriksargsyan.bitcointraineetestapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.eriksargsyan.bitcointraineetestapp.network.CryptoAPI
import com.eriksargsyan.bitcointraineetestapp.network.CryptoApiConstants.BASE_URL
import com.eriksargsyan.bitcointraineetestapp.util.Dispatchers
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

    @Provides
    @Singleton
    fun provideCryptoAPI(retrofit: Retrofit): CryptoAPI =
        retrofit.create(CryptoAPI::class.java)


    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher =
        Dispatchers.IO

}