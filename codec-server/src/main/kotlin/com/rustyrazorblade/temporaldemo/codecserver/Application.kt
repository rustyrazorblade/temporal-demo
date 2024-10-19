package com.rustyrazorblade.temporaldemo.codecserver

import com.rustyrazorblade.temporaldemo.SampleCodec
import com.rustyrazorblade.temporaldemo.codecserver.plugins.configureRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.temporal.payload.codec.ChainCodec

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CORS) {
        println("installing cors")
        //allowOrigins { true }
        anyHost()
        anyMethod()
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowHeaders { true }
    }
    configureRouting()
}


val JSON_FORMAT = com.google.protobuf.util.JsonFormat.parser()
val JSON_PRINTER = com.google.protobuf.util.JsonFormat.printer()
val CODEC = ChainCodec(listOf(SampleCodec()))


