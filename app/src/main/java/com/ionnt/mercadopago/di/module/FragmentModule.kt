package com.ionnt.mercadopago.di.module

import com.ionnt.mercadopago.di.annotations.FragmentScope
import com.ionnt.mercadopago.ui.fragments.PayBankFragment
import com.ionnt.mercadopago.ui.fragments.PayCompleteFragment
import com.ionnt.mercadopago.ui.fragments.PayMainFragment
import com.ionnt.mercadopago.ui.fragments.PayMethodFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayMainFragment(): PayMainFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayMethodFragment(): PayMethodFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayBankFragment(): PayBankFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayCompleteFragment(): PayCompleteFragment
}