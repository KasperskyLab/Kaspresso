package com.kaspersky.kaspresso.tutorial

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WifiSampleEspressoTest {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        // launch target screen
        onView(withId(R.id.wifi_activity_btn)).apply {
            check(matches(isDisplayed()))
            check(matches(isClickable()))
            perform(ViewActions.click())
        }

        // set portrait orientation
        val uiAutomation = InstrumentationRegistry.getInstrumentation().uiAutomation
        uiAutomation.executeShellCommand(SHELL_COMMAND_AUTO_ROTATE_DISABLE)
        uiAutomation.executeShellCommand(SHELL_COMMAND_PORTRAIT_ORIENTATION)

        // test
        onView(withId(R.id.wifi_status)).check(matches(withText("")))
        onView(withId(R.id.check_wifi_btn)).apply {
            check(matches(isDisplayed()))
            check(matches(isClickable()))
            perform(ViewActions.click())
        }
        onView(withId(R.id.wifi_status)).check(matches(withText(R.string.enabled_status)))

        // Turning off wifi
        uiAutomation.executeShellCommand(SHELL_COMMAND_TURN_OFF_WIFI)

        // wait for switching wifi
        Thread.sleep(3000)

        // test
        onView(withId(R.id.check_wifi_btn)).perform(ViewActions.click())
        onView(withId(R.id.wifi_status)).check(matches(withText(R.string.disabled_status)))

        //rotate
        uiAutomation.executeShellCommand(SHELL_COMMAND_AUTO_ROTATE_DISABLE)
        uiAutomation.executeShellCommand(SHELL_COMMAND_LANDSCAPE_ORIENTATION)

        // wait for rotation
        Thread.sleep(3000)

        // test
        onView(withId(R.id.wifi_status)).check(matches(withText(R.string.disabled_status)))
    }

    private companion object {

        const val SHELL_COMMAND_AUTO_ROTATE_DISABLE = "content insert --uri content://settings/system --bind name:s:accelerometer_rotation --bind value:i:0"
        const val SHELL_COMMAND_PORTRAIT_ORIENTATION = "content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:0"
        const val SHELL_COMMAND_LANDSCAPE_ORIENTATION = "content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:1"
        const val SHELL_COMMAND_TURN_OFF_WIFI = "svc wifi disable"
    }
}
