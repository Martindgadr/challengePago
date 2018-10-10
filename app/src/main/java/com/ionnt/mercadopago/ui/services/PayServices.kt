package com.ionnt.mercadopago.ui.services

import com.ionnt.mercadopago.commons.Constants
import com.ionnt.mercadopago.model.CardIssuers
import com.ionnt.mercadopago.model.Installments
import com.ionnt.mercadopago.model.PaymentMethod
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Martin De Girolamo on 03/10/2018.
 */
interface PayServices {
    @GET(Constants.GET_PAYMENT_METHODS)
    fun getPaymentMethods(@Query(Constants.QUERY_PUBLIC_KEY) api_key: String)
            : Observable<Response<List<PaymentMethod>>>

    @GET(Constants.GET_PAYMENT_INSTALLMENTS)
    fun getPaymentInstallments(@Query(Constants.QUERY_PUBLIC_KEY) api_key: String, @Query(Constants.QUERY_AMOUNT) amount: Float, @Query(Constants.QUERY_PAYMENT_METHOD) payId: String, @Query(Constants.QUERY_ISSUER_ID) id: String)
            : Observable<Response<List<Installments>>>

    @GET(Constants.GET_CARD_ISSUERS)
    fun getCardIssuers(@Query(Constants.QUERY_PUBLIC_KEY) api_key: String, @Query(Constants.QUERY_PAYMENT_METHOD) method: String)
            : Observable<Response<List<CardIssuers>>>

}