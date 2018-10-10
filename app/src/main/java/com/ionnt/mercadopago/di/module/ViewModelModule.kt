package com.ionnt.mercadopago.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ionnt.mercadopago.commons.factory.ViewModelFactory
import com.ionnt.mercadopago.di.annotations.ViewModelKey
import com.ionnt.mercadopago.ui.viewmodel.PayViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel::class)
    abstract fun bindPayViewModel(payViewModel: PayViewModel): ViewModel
}