package com.ionnt.mercadopago.commons.extension

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.ionnt.mercadopago.commons.base.BaseActivity
import com.ionnt.mercadopago.commons.base.BaseFragment
import kotlinx.android.synthetic.main.activity_pay.*

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragment_container
val BaseFragment.appContext: Context get() = activity?.applicationContext!!