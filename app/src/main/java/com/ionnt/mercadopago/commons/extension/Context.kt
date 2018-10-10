package com.ionnt.mercadopago.commons.extension

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

val Context.networkState: NetworkInfo? @SuppressLint("MissingPermission")
get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo