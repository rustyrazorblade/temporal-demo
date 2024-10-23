package com.rustyrazorblade.temporaldemo

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import java.math.BigDecimal
import java.util.Currency

@ActivityInterface
interface ProcessPaymentActivities {
    @ActivityMethod
    fun chargeCreditCard(ccNumber: String, amount: BigDecimal): Boolean

    @ActivityMethod
    fun saveTransaction(transaction: String): Boolean

    @ActivityMethod
    fun sendReceipt(amount: BigDecimal, email: String): Boolean
}