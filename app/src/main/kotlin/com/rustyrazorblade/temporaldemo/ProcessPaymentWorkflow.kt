package com.rustyrazorblade.temporaldemo;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ProcessPaymentWorkflow {
    @WorkflowMethod
    fun processTransaction(details: ProcessPaymentDetails): Boolean
}
