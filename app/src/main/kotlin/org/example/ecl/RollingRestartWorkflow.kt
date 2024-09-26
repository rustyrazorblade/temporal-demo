package org.example.ecl

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface RollingRestartWorkflow {
    @WorkflowMethod
    fun perform(params: RollingRestartParams): List<Boolean>
}