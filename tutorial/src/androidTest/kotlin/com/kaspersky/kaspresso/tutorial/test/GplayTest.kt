package com.kaspersky.kaspresso.tutorial.test

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.GplayScreen
import org.junit.Test

class GplayTest: TestCase() {

    @Test
    fun testNotSignedIn() = run {
        step("forceStop Gplay application") {
            adbServer.performShell("am force-stop $GPLAY_PACKAGE")
            adbServer.performShell("pm clear $GPLAY_PACKAGE")
        }
        step("open Gplay app") {
            with(device.targetContext) {
                val intent = packageManager.getLaunchIntentForPackage(GPLAY_PACKAGE)
                startActivity(intent)
            }
            device.uiDevice.waitForIdle()
        }

        step("verify sign in needed") {
            GplayScreen {
                signInButton.isDisplayed()
            }
        }
    }

    companion object {
        private const val GPLAY_PACKAGE = "com.android.vending"
    }
}
