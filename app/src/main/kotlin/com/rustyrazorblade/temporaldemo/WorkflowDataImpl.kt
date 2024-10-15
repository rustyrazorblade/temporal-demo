package com.rustyrazorblade.temporaldemo

import com.fasterxml.jackson.annotation.JsonProperty

class WorkflowDataImpl(@JsonProperty("something") private val something: String) :
    com.rustyrazorblade.temporaldemo.WorkflowData {
    override fun getSomething(): String {
        return something
    }
}
