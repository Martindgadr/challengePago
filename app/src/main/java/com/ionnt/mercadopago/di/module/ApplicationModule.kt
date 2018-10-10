package com.ionnt.mercadopago.di.module

import com.ionnt.mercadopago.MercadoPagoApplication
import com.ionnt.mercadopago.commons.Constants
import com.ionnt.mercadopago.ui.repositories.PayRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.END_POINT)
                .client(createClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: MercadoPagoApplication) = application.applicationContext

    @Provides
    @Singleton
    fun providePayRepository(retrofit: Retrofit): PayRepository = PayRepository(retrofit)

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }
}