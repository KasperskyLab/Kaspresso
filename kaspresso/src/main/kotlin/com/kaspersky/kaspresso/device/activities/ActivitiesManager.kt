package com.kaspersky.kaspresso.device.activities

import android.app.Activity

/**
 * An interface to work with activities.
 */
interface ActivitiesManager {

    /**
     * Finds and returns resumed activity if it exists.
     *
     * @return nullable resumed activity
     */
    fun getResumedActivity(): Activity?

    /**
     * Checks if passed activity is resumed.
     */
    fun isCurrentActivity(clazz: Class<out Activity>)
}