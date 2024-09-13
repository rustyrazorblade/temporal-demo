package org.example

import com.fasterxml.jackson.annotation.JsonProperty

class WorkflowDataImpl(@JsonProperty("something") private val something: String) : WorkflowData {
    override fun getSomething(): String {
        return something
    }
}
