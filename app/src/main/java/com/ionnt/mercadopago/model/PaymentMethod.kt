package com.ionnt.mercadopago.model

/**
 * Created by Martin De Girolamo on 04/10/2018.
 */
class PaymentMethod(var id: String,
                    var name: String,
                    var payment_type_id: String,
                    var status: String,
                    var secure_thumbnail: String,
                    var thumbnail: String,
                    var eferred_capture: String,
                    //TODO Modelar Settings
                    //var settings: List<String>,
                    var additional_info_needed: List<String>,
                    var min_allowed_amount: Float,
                    var max_allowed_amount: Float,
                    var financial_institutions: List<String>,
                    var processing_modes: List<String>,
                    var accreditation_time: Int
)