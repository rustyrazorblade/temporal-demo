package com.rustyrazorblade.temporaldemo

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface MyActivities {
    @ActivityMethod
    fun doSomething(s: String) : String

    @ActivityMethod
    fun doSomethingElse(s: String) : Int
}
