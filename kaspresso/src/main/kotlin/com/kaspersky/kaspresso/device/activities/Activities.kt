package com.kaspersky.kaspresso.device.activities

import android.app.Activity

/**
 * The interface to work with activities.
 */
interface Activities {

    /**
     * Finds and returns resumed activity if it exists.
     *
     * @return nullable resumed activity.
     */
    fun getResumed(): Activity?

    /**
     * Checks if passed activity is resumed.
     */
    fun isCurrent(clazz: Class<out Activity>)
}
