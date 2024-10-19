package com.rustyrazorblade.temporaldemo

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.rustyrazorblade.temporaldemo.ecl.RollingRestartActivitiesImpl
import com.rustyrazorblade.temporaldemo.ecl.RollingRestartWorkflowImpl
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory
import java.net.InetAddress
import io.temporal.common.converter.CodecDataConverter
import io.temporal.common.converter.DefaultDataConverter


fun main(arguments: Array<String>) {

    val args = object {
        @Parameter(names = ["--host"], description = "Temporal server host")
        var host: String = "localhost"

        @Parameter(names = ["--port"], description = "Temporal server port")
        var port: Int = 7233
    }

    JCommander.newBuilder().programName("temporal-demo")
        .addObject(args)
        .build()
        .parse(*arguments)

    /**
     * Worker needs a service client and factory
     * The worker sets the implementation of the workflow interface
     *
     */

    println("Connecting to temporal server: ${args.host}")
    val address = InetAddress.getByName(args.host)
    println("Resolved host: ${address.hostAddress}")

    val target = "${args.host}:${args.port}"


    val serviceOptions = WorkflowServiceStubsOptions.newBuilder()
        .setTarget(target)
        .build()
    val service = WorkflowServiceStubs.newServiceStubs(serviceOptions)

    /**
     * A namespace is an isolated resource, and a client only talks to one namespace.
     * A single cluster can host multiple namespaces.
     * You can scale workers / clients up or down for a given namespace.
     * Temporal suggests using namespaces to isolate resources such as "dev" or "prod".
     * I prefer to keep my production resources completely isolated from dev, so I think it would
     * be better to have two environments, especially if we run Temporal server instead of cloud,
     * we will need a way to test changes.
     * So dev should have an isolated dev environment.
     */
    val options = WorkflowClientOptions.newBuilder()
        .setNamespace("default")
        .setDataConverter(CodecDataConverter(DefaultDataConverter.newDefaultInstance(), listOf(SampleCodec())))
        .build()

    val client = WorkflowClient.newInstance(service, options)

    val factory = WorkerFactory.newInstance(client)

    val worker = factory.newWorker(Shared.DEMO_TASK_QUEUE)

    worker.registerWorkflowImplementationTypes(TestWorkflowImpl::class.java)
    worker.registerWorkflowImplementationTypes(RollingRestartWorkflowImpl::class.java)

    // if we need a DB connection, we created it first, then pass it to the Activities implementation
    // so for example create a java DB pool here or connect to a C* cluster
    worker.registerActivitiesImplementations(MyActivitiesImpl())
    worker.registerActivitiesImplementations(RollingRestartActivitiesImpl())

    for (i in 0..10) {
        try {
            println("Starting worker $target")
            factory.start()
            break
        } catch (e: Exception) {
            println("Error starting worker: $e")
            e.printStackTrace()
            Thread.sleep(5000)
        }
    }
    println("Running")
}

