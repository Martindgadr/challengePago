package com.ionnt.mercadopago.ui.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ionnt.mercadopago.R
import com.ionnt.mercadopago.model.PayerCosts

/**
 * Created by Martin De Girolamo on 04/10/2018.
 */
class PayCompleteViewAdapter(private var mValues: List<PayerCosts>, private val clickListener: (PayerCosts) -> Unit) : RecyclerView.Adapter<PayCompleteViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pay_payer_costst, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mItem = item
        holder.mName.text = item.recommended_message
        holder.mContainer.setOnClickListener {
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun refreshList(mList: List<PayerCosts>) {
        mValues = mList
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mContainer: ConstraintLayout = mView.findViewById(R.id.pay_container)
        val mName: TextView = mView.findViewById(R.id.payer_txt)
        var mItem: PayerCosts? = null
    }
}