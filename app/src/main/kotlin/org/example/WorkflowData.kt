package org.example

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(`as` = WorkflowDataImpl::class)
interface WorkflowData {
    fun getSomething(): String
}