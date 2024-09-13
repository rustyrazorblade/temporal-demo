package org.example

class MyActivitiesImpl : MyActivities {
    override fun doSomething(s: String): String {
        return "OMG! $s"
    }

    override fun doSomethingElse(s: String): Int {
        return s.length
    }
}