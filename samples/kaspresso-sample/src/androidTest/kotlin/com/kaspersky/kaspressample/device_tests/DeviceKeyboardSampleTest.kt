package com.kaspersky.kaspressample.device_tests

import android.view.KeyEvent
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.screen.DeviceSampleScreen
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class DeviceKeyboardSampleTest : TestCase() {

    companion object {
        const val TEXT_TO_BE_TYPED = "Kaspresso"
    }

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun keyboardSampleTest() {
        run {

            /**
             * Consider to use [ViewActions.typeText] or [UiObject.setText] instead of [Keyboard.typeText]
             */
            step("Type text using [Keyboard.typeText]") {
                DeviceSampleScreen {
                    input {
                        device.keyboard.typeText(TEXT_TO_BE_TYPED)
                        hasText(TEXT_TO_BE_TYPED)
                    }
                }
            }

            /**
             * Consider to use [ViewActions.pressKey] or [UiDevice.pressKeyCode] instead of [Keyboard.sendEvent]
             */
            step("Type text using [Keyboard.sendEvent]") {
                DeviceSampleScreen {
                    input {
                        clearText()
                    }
                }

                // Type `kaspresso`
                device.keyboard.sendEvent(KeyEvent.KEYCODE_K)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_A)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_S)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_P)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_R)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_E)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_S)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_S)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_O)

                DeviceSampleScreen {
                    input {
                        hasText("kaspresso")
                    }
                }
            }
        }
    }
}
