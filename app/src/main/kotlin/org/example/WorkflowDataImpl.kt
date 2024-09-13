package org.example

class WorkflowDataImpl(private val something: String) : WorkflowData {
    override fun getSomething(): String {
        return something
    }
}
