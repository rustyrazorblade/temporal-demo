package com.rustyrazorblade.temporaldemo

class MyActivitiesImpl : MyActivities {
    override fun doSomething(s: String): String {
        Thread.sleep(5000)
        println(s)
        return "OMG! $s"
    }

    override fun doSomethingElse(s: String): Int {
        Thread.sleep(2000)
        return s.length
    }
}