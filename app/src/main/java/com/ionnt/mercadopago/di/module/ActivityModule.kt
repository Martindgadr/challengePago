package com.ionnt.mercadopago.di.module

import com.ionnt.mercadopago.di.annotations.ActivityScope
import com.ionnt.mercadopago.ui.PayActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    @ActivityScope
    abstract fun contributePayActivity(): PayActivity
}