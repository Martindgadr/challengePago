package com.ionnt.mercadopago.commons.utils

import android.content.Context
import com.ionnt.mercadopago.commons.extension.networkState
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 03/10/2018.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkState?.isConnectedOrConnecting
}