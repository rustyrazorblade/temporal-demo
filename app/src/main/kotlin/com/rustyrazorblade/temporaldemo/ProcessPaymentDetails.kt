package com.rustyrazorblade.temporaldemo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.math.BigDecimal
import java.util.Currency

@JsonDeserialize(`as` = ProcessPaymentDetailsImpl::class)
interface ProcessPaymentDetails {
    fun getCreditCardNumber(): String
    fun getAmount(): BigDecimal
    fun getEmail(): String
}
