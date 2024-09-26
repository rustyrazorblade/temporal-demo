package org.example

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import org.example.ecl.RollingRestartActivitiesImpl
import org.example.ecl.RollingRestartWorkflowImpl

fun main() {

    /**
     * Worker needs a service client and factory
     * The worker sets the implementation of the workflow interface
     *
     */
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val factory = WorkerFactory.newInstance(client)

    val worker = factory.newWorker(Shared.DEMO_TASK_QUEUE)

    worker.registerWorkflowImplementationTypes(TestWorkflowImpl::class.java)
    worker.registerWorkflowImplementationTypes(RollingRestartWorkflowImpl::class.java)

    // if we need a DB connection, we created it first, then pass it to the Activities implementation
    // so for example create a java DB pool here or connect to a C* cluster
    worker.registerActivitiesImplementations(MyActivitiesImpl())
    worker.registerActivitiesImplementations(RollingRestartActivitiesImpl())
    println("Starting Worker")
    factory.start()
    println("Stared, running")
}

