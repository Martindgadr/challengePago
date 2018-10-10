package com.ionnt.mercadopago.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ionnt.mercadopago.R
import com.ionnt.mercadopago.commons.base.BaseActivity
import com.ionnt.mercadopago.commons.base.BaseFragment
import com.ionnt.mercadopago.commons.extension.replaceFragment
import com.ionnt.mercadopago.commons.utils.AlertUtil
import com.ionnt.mercadopago.commons.utils.MoneyTextWatcher
import com.ionnt.mercadopago.ui.PayActivity
import com.ionnt.mercadopago.ui.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.fragment_pay_main.view.*

/**
 * Created by Martin De Girolamo on 03/10/2018.
 */
class PayMainFragment : BaseFragment() {

    private lateinit var viewModel: PayViewModel

    override fun layoutId(): Int = R.layout.fragment_pay_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PayViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        savedInstanceState?.let {
            rootView.fr_pay_main_amount.setText(it.get((activity as PayActivity).AMOUNT).toString())
        }
        addListener(rootView)
        return rootView
    }

    private fun addListener(rootView: View) {
        rootView.fr_pay_main_amount.addTextChangedListener(MoneyTextWatcher(rootView.fr_pay_main_amount))
        rootView.fr_pay_main_button.setOnClickListener {
            val amount = rootView.fr_pay_main_amount.text.toString().replace("$", "").replace(",", "").toFloat()
            if (amount > 0.0) {
                (activity as PayActivity).amountPay = amount
                (activity as BaseActivity).replaceFragment(false, R.id.fragment_container, PayMethodFragment.newInstance(), Bundle(), true)
            } else {
                activity?.let { act -> AlertUtil.showPopup(act, R.drawable.ic_payment, R.string.app_name, R.string.invalid_amount) }
            }
        }
    }

    companion object {
        fun newInstance(): PayMainFragment {
            return PayMainFragment()
        }
    }
}