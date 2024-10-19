package com.rustyrazorblade.temporaldemo.codecserver.plugins

import com.rustyrazorblade.temporaldemo.codecserver.CODEC
import com.rustyrazorblade.temporaldemo.codecserver.JSON_FORMAT
import com.rustyrazorblade.temporaldemo.codecserver.JSON_PRINTER
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.temporal.api.common.v1.Payload
import io.temporal.api.common.v1.Payloads
import java.nio.charset.Charset

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("{}", ContentType.Application.Json)
        }
        // TODO (jwest): refactor similar code
        post("/decode") {
            val jsonInputStream = call.receiveStream().reader(Charset.defaultCharset())
            val incomingPayloads = Payloads.newBuilder()
            JSON_FORMAT.merge(jsonInputStream, incomingPayloads)

            val incomingPayloadList = incomingPayloads.build().payloadsList
            println("incomingPayloadList: $incomingPayloadList")

            val outgoingPayloadsList: List<Payload> = CODEC.decode(incomingPayloadList)

            // TODO: prefer something that uses output stream and JSON_PRINTER.appendTo  (see respondOutputStream)
            call.respondText(JSON_PRINTER.print(Payloads.newBuilder().addAllPayloads(outgoingPayloadsList).build()), ContentType.Application.Json)
        }

        post("/encode") {
            val jsonInputStream = call.receiveStream().reader(Charset.defaultCharset())
            val incomingPayloads = Payloads.newBuilder()
            JSON_FORMAT.merge(jsonInputStream, incomingPayloads)

            val incomingPayloadList = incomingPayloads.build().payloadsList
            println("incomingPayloadList: $incomingPayloadList")

            val outgoingPayloadsList: List<Payload> = CODEC.encode(incomingPayloadList)
            println("outgoingPayloadsList: $outgoingPayloadsList")
            call.respondText(JSON_PRINTER.print(Payloads.newBuilder().addAllPayloads(outgoingPayloadsList).build()), ContentType.Application.Json)
        }
    }
}
