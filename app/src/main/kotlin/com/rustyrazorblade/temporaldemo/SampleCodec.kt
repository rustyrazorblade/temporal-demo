package com.rustyrazorblade.temporaldemo

import com.google.protobuf.ByteString
import io.temporal.api.common.v1.Payload
import io.temporal.common.converter.EncodingKeys
import io.temporal.payload.codec.PayloadCodec

class SampleCodec : PayloadCodec {
    override fun encode(payloads: MutableList<Payload>): List<Payload> {
        return payloads.map { encodePayload(it) }
    }

    override fun decode(payloads: MutableList<Payload>): List<Payload> {
        return payloads.map { decodePayload(it) }
    }

    private fun encodePayload(payload: Payload): Payload {
        return Payload.newBuilder()
                .putMetadata(EncodingKeys.METADATA_ENCODING_KEY, METATADATA_ENCODING_SAMPLE)
                .setData(ByteString.copyFrom(payload.toByteArray().reversedArray()))
                .build()
    }

    private fun decodePayload(payload: Payload): Payload {
        if (METATADATA_ENCODING_SAMPLE.equals(payload.getMetadataOrDefault(EncodingKeys.METADATA_ENCODING_KEY, ByteString.EMPTY))) {
            payload.data.newInput().use {
                return Payload.parseFrom(it.readBytes().reversedArray())
            }
        } else {
            return payload
        }
    }

    companion object {
        val METATADATA_ENCODING_SAMPLE = ByteString.copyFromUtf8("binary/sample")
    }
}