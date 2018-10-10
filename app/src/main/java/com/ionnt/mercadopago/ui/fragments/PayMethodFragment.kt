package com.ionnt.mercadopago.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ionnt.mercadopago.R
import com.ionnt.mercadopago.commons.base.BaseActivity
import com.ionnt.mercadopago.commons.base.BaseFragment
import com.ionnt.mercadopago.commons.extension.replaceFragment
import com.ionnt.mercadopago.databinding.FragmentPayMethodBinding
import com.ionnt.mercadopago.model.PaymentMethod
import com.ionnt.mercadopago.ui.PayActivity
import com.ionnt.mercadopago.ui.adapter.PayMethodViewAdapter
import com.ionnt.mercadopago.ui.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.fragment_pay_method.view.*
import kotlin.properties.Delegates

/**
 * Created by Martin De Girolamo on 05/10/2018.
 */
class PayMethodFragment : BaseFragment() {

    private lateinit var viewModel: PayViewModel

    override fun layoutId(): Int = R.layout.fragment_pay_method

    private lateinit var adapterPay: PayMethodViewAdapter

    private var mBinding: FragmentPayMethodBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PayViewModel::class.java)
        viewModel.getPaymentMethods()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater,layoutId(), container, false)
        mBinding.viewModel = viewModel
        val rootView = mBinding.root
        loadAdapter(rootView)
        return rootView
    }

    private fun loadAdapter(rootView: View) {
        adapterPay = PayMethodViewAdapter(ArrayList()) { item: PaymentMethod -> onItemClick(item) }
        rootView.fr_pay_method_recycler.adapter = adapterPay
        rootView.fr_pay_method_recycler.layoutManager = LinearLayoutManager(activity)
        rootView.fr_pay_method_recycler.isVerticalScrollBarEnabled = true
        rootView.fr_pay_method_recycler.isNestedScrollingEnabled = true
    }

    private fun onItemClick(item: PaymentMethod) {
        (activity as PayActivity).paymentMethod = item
        (activity as BaseActivity).replaceFragment(false, R.id.fragment_container, PayBankFragment.newInstance(), Bundle(), true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.paymentMethods.reObserve(this, Observer { loadPaymentMethod(it) })
        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })
    }

    private fun loadPaymentMethod(list: List<PaymentMethod>?) {
        list?.let {
            adapterPay.refreshList(it)
        }
    }

    companion object {
        fun newInstance() : PayMethodFragment {
            return PayMethodFragment()
        }
    }

}