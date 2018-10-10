package com.ionnt.mercadopago.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.ionnt.mercadopago.commons.utils.NetworkHandler
import com.ionnt.mercadopago.model.CardIssuers
import com.ionnt.mercadopago.model.Installments
import com.ionnt.mercadopago.model.PaymentMethod
import com.ionnt.mercadopago.ui.repositories.PayRepository
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 03/10/2018.
 */
class PayViewModel @Inject constructor(private val payRepository: PayRepository, private val networkHandler: NetworkHandler) : ViewModel() {

    var paymentMethods: MutableLiveData<List<PaymentMethod>> = MutableLiveData()
    var cardIssuers: MutableLiveData<List<CardIssuers>> = MutableLiveData()
    var installments: MutableLiveData<List<Installments>> = MutableLiveData()

    var errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    var errorService: MutableLiveData<Boolean> = MutableLiveData()

    var showProgressObs = ObservableField<Boolean>()

    init {
        showProgressObs.set(false)
    }

    fun getPaymentMethods() {
        when (networkHandler.isConnected) {
            true -> {
                showProgressObs.set(true)
                payRepository.getPaymentMethods().subscribe({ paymentList ->
                    showProgressObs.set(false)
                    paymentMethods.value = paymentList
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    fun getCardIssuers(payMethodId : String) {
        when (networkHandler.isConnected) {
            true -> {
                showProgressObs.set(true)
                payRepository.getCardIssuers(payMethodId).subscribe({ cardIssuersList ->
                    showProgressObs.set(false)
                    cardIssuers.value = cardIssuersList
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }


    fun getInstallments(amount: Float, payMethodId: String, issuerId: String) {
        when (networkHandler.isConnected) {
            true -> {
                showProgressObs.set(true)
                payRepository.getInstallments(amount,payMethodId,issuerId).subscribe({ installmentsList ->
                    showProgressObs.set(false)
                    installments.value = installmentsList
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    private fun handleError(error: Throwable) {
        showProgressObs.set(false)
        Log.e(PayViewModel::class.java.name, error.message + error.cause)
        errorService.value = true
    }
}