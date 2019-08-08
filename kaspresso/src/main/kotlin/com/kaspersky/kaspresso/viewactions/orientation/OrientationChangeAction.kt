package com.kaspersky.kaspresso.viewactions.orientation

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.Matcher

/**
 * An implementation of device's orientation change.
 */
class OrientationChangeAction(
    private val activity: Activity,
    private val orientation: Int? = null
) : ViewAction {

    companion object {

        fun toggle(activity: Activity): ViewAction =
            OrientationChangeAction(activity)

        fun orientationLandscape(activity: Activity): ViewAction =
            OrientationChangeAction(
                activity,
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            )

        fun orientationPortrait(activity: Activity): ViewAction =
            OrientationChangeAction(
                activity,
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            )
    }

    override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()

    override fun getDescription(): String {
        return when (orientation) {
            null -> "toggle orientation"
            else -> "change orientation to $orientation"
        }
    }

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadUntilIdle()
        activity.requestedOrientation = orientation ?: decideOrientationToToggle(activity)
        uiController.loopMainThreadUntilIdle()
    }

    private fun decideOrientationToToggle(activity: Activity): Int {
        val currentOrientation = activity.resources.configuration.orientation

        return when (currentOrientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            else -> {
                throw IllegalStateException("illegal orientation: $currentOrientation")
            }
        }
    }
}