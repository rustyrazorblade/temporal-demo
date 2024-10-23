package com.rustyrazorblade.temporaldemo

import java.math.BigDecimal
import java.util.*

class ProcessPaymentActivitiesImpl : ProcessPaymentActivities {
    override fun chargeCreditCard(ccNumber: String, amount: BigDecimal): Boolean {
        // our fake payment processor is very unreliable
        if (Random().nextBoolean()) {
            throw RuntimeException("credit card error")
        }
        return true
    }

    override fun saveTransaction(transaction: String): Boolean {
        return true
    }

    override fun sendReceipt(amount: BigDecimal, email: String): Boolean {
        return true
    }
}