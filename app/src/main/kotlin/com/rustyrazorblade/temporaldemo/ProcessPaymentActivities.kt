package com.rustyrazorblade.temporaldemo

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import java.util.Currency

@ActivityInterface
interface ProcessPaymentActivities {
    @ActivityMethod
    fun chargeCreditCard(ccNumber: String, amount: Currency): TransactionResult

    @ActivityMethod
    fun saveTransaction(transaction: TransactionResult): Boolean

    @ActivityMethod
    fun sendReceipt(amount: Currency, email: String): Boolean
}