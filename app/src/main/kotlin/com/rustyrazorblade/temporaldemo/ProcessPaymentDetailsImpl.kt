package com.rustyrazorblade.temporaldemo

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.util.Currency

class ProcessPaymentDetailsImpl(
    @JsonProperty("creditCardNumber") private val creditCardNumber: String,
    @JsonProperty("amount") private val amount: BigDecimal,
    @JsonProperty("email") private val email: String

) : ProcessPaymentDetails {
    override fun getCreditCardNumber(): String {
        return creditCardNumber
    }

    override fun getAmount(): BigDecimal {
        return amount
    }

    override fun getEmail(): String {
        return email
    }

}
