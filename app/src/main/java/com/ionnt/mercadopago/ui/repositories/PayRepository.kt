package com.ionnt.mercadopago.ui.repositories

import com.ionnt.mercadopago.commons.Constants
import com.ionnt.mercadopago.model.CardIssuers
import com.ionnt.mercadopago.model.Installments
import com.ionnt.mercadopago.model.PaymentMethod
import com.ionnt.mercadopago.ui.services.PayServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 03/10/2018.
 */
class PayRepository @Inject constructor(retrofit: Retrofit) {

    private val payApi by lazy { retrofit.create(PayServices::class.java) }

    fun getPaymentMethods(): Observable<List<PaymentMethod>> {
        return payApi.getPaymentMethods(Constants.PUBLIC_KEY)
                .concatMap { httpValidation(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCardIssuers(payMethodId: String): Observable<List<CardIssuers>> {
        return payApi.getCardIssuers(Constants.PUBLIC_KEY, payMethodId)
                .concatMap { httpValidation(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getInstallments(amount: Float, payMethodId: String, issuerId: String) : Observable<List<Installments>> {
        return payApi.getPaymentInstallments(Constants.PUBLIC_KEY, amount,payMethodId,issuerId)
                .concatMap { httpValidation(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> httpValidation(response: Response<T>): Observable<T> {
        return Observable.fromCallable {
            if (response.isSuccessful) {
                response.body()?.let {
                    it
                }
            } else throw getExceptionHandler(response.code(), response.errorBody())
        }
    }

    private fun getExceptionHandler(code: Int, errorBody: ResponseBody?): Throwable {
        when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> throw Exception("Service request not found")
            HttpURLConnection.HTTP_UNAUTHORIZED -> throw Exception("User not authorized to access services")
            HttpURLConnection.HTTP_CONFLICT -> throw Exception("User token auth still available")
            else -> throw Exception("Unknown error")
        }
    }
}