package com.rustyrazorblade.temporaldemo

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class ProcessPaymentWorkflowImpl : ProcessPaymentWorkflow {
        val activityOptions = ActivityOptions.newBuilder()
            .setRetryOptions(RetryOptions.newBuilder().setBackoffCoefficient(3.0).build())
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .build()

        val processPaymentActivities = Workflow.newActivityStub(ProcessPaymentActivities::class.java, activityOptions)

    override fun processTransaction(details: ProcessPaymentDetails): Boolean {
        processPaymentActivities.chargeCreditCard(details.getCreditCardNumber(), details.getAmount())
        // TODO update this to use a Transaction type in the return type
        processPaymentActivities.saveTransaction("transaction")
        processPaymentActivities.sendReceipt(details.getAmount(), details.getEmail())
        return true
    }
}