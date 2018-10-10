package com.ionnt.mercadopago.ui

import android.os.Bundle
import com.google.gson.Gson
import com.ionnt.mercadopago.R
import com.ionnt.mercadopago.commons.base.BaseActivity
import com.ionnt.mercadopago.commons.extension.replaceFragment
import com.ionnt.mercadopago.model.CardIssuers
import com.ionnt.mercadopago.model.PaymentMethod
import com.ionnt.mercadopago.ui.fragments.PayMainFragment

class PayActivity : BaseActivity() {

    val AMOUNT : String = "amountPay"
    val PAYMENT : String = "paymentMethod"
    val ISSUERS : String = "cardIssuers"

    // Input Data
    var amountPay : Float = 0f
    var paymentMethod : PaymentMethod? = null
    var cardIssuers : CardIssuers? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState?.let {
            it.putFloat(AMOUNT,amountPay)
            it.putString(PAYMENT,Gson().toJson(paymentMethod))
            it.putString(ISSUERS,Gson().toJson(cardIssuers))
        }
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        amountPay = savedInstanceState.getFloat(AMOUNT)
        paymentMethod = Gson().fromJson(savedInstanceState.getString(PAYMENT),PaymentMethod::class.java)
        cardIssuers = Gson().fromJson(savedInstanceState.getString(ISSUERS),CardIssuers::class.java)
    }

    fun restartActivity() {
        clearData()
        replaceFragment(false, R.id.fragment_container, PayMainFragment.newInstance(), Bundle(), false)
    }

    private fun clearData() {
        amountPay = 0f
        paymentMethod = null
        cardIssuers = null
    }

    override fun initializeFragment() {
        replaceFragment(false, R.id.fragment_container, PayMainFragment.newInstance(), Bundle(), true)
    }
}