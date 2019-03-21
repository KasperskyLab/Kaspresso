package com.kaspersky.kaspresso.device.activities

import android.app.Activity

interface ActivitiesManager {

    fun getResumedActivity(): Activity?

    fun isCurrentActivity(clazz: Class<out Activity>)
}