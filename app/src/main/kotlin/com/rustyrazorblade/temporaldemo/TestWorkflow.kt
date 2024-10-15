package com.rustyrazorblade.temporaldemo

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface TestWorkflow {
    @WorkflowMethod
    fun starCoolWorkflow(data: WorkflowData): Int
}
