package com.rustyrazorblade.temporaldemo.ecl

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface RollingRestartActivities {
     @ActivityMethod
     fun restartNode(sshKeyPath: String, ip: String): Boolean

}