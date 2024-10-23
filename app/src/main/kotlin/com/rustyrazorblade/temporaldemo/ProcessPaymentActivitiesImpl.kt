package com.rustyrazorblade.temporaldemo

import java.util.*

class ProcessPaymentActivitiesImpl : ProcessPaymentActivities {
    override fun chargeCreditCard(ccNumber: String, amount: Currency): TransactionResult {
        // our fake payment processor is very unreliable
        if (Random().nextBoolean()) {
            throw RuntimeException("credit card error")
        }
        return TransactionResult("success")
    }

    override fun saveTransaction(transaction: TransactionResult): Boolean {
        return true
    }

    override fun sendReceipt(amount: Currency, email: String): Boolean {
        TODO("Not yet implemented")
    }
}