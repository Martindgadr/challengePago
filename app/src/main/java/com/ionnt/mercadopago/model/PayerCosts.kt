package com.ionnt.mercadopago.model

/**
 * Created by Martin De Girolamo on 04/10/2018.
 */
class PayerCosts(var installments: Int,
               var installment_rate: Float
                 ,
               var discount_rate: Int,
               var labels: List<String>,
               var installment_rate_collector: List<String>,
               var min_allowed_amount: Int,
               var max_allowed_amount: Int,
               var recommended_message: String,
               var installment_amount: Float,
               var total_amount: Float)