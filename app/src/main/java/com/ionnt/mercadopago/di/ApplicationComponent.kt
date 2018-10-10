package com.ionnt.mercadopago.di

import com.ionnt.mercadopago.MercadoPagoApplication
import com.ionnt.mercadopago.di.module.ActivityModule
import com.ionnt.mercadopago.di.module.ApplicationModule
import com.ionnt.mercadopago.di.module.FragmentModule
import com.ionnt.mercadopago.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 03/10/2018.
 */

@Singleton
@Component(modules = [(ApplicationModule::class), (ViewModelModule::class), (ActivityModule::class), (FragmentModule::class), (AndroidInjectionModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MercadoPagoApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MercadoPagoApplication)
}