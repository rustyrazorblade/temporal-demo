package com.rustyrazorblade.temporaldemo

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class TestWorkflowImpl : TestWorkflow {

    val activityOptions = ActivityOptions.newBuilder()
        .setRetryOptions(RetryOptions.newBuilder().setBackoffCoefficient(3.0).build())
        .setStartToCloseTimeout(Duration.ofSeconds(10))
        .build()

    val activities = Workflow.newActivityStub(MyActivities::class.java, activityOptions)

    override fun starCoolWorkflow(data: WorkflowData) : Int {
        println("calling doSomething()")
        val temp = activities.doSomething(data.getSomething())

        println("Calling doSomethingElse()")
        val result = activities.doSomethingElse(temp)

        println("Result: [$result]")
        return result
    }
}