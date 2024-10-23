package com.rustyrazorblade.temporaldemo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Currency

@JsonDeserialize(`as` = ProcessPaymentDetailsImpl::class)
interface ProcessPaymentDetails {
    fun getCreditCardNumber(): String
    fun getAmount(): Currency
    fun getEmail(): String
}
