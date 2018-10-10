package com.ionnt.mercadopago.ui.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ionnt.mercadopago.R
import com.ionnt.mercadopago.model.PaymentMethod

/**
 * Created by Martin De Girolamo on 05/10/2018.
 */
class PayMethodViewAdapter(private var mValues: List<PaymentMethod>, private val clickListener: (PaymentMethod) -> Unit) : RecyclerView.Adapter<PayMethodViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pay_method, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mItem = item
        holder.mName.text = item.name

        if (item.thumbnail.isNotEmpty()) Glide.with(holder.mImage.context).load(item.thumbnail).into(holder.mImage)

        holder.mContainer.setOnClickListener {
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun refreshList(mList: List<PaymentMethod>) {
        mValues = mList
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mContainer: ConstraintLayout = mView.findViewById(R.id.pay_container)
        val mName: TextView = mView.findViewById(R.id.pay_txt)
        val mImage: ImageView = mView.findViewById(R.id.pay_image)
        var mItem: PaymentMethod? = null
    }
}