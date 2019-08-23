package com.kaspersky.kaspressample.tests.device

import android.Manifest
import android.provider.CallLog
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DevicePhoneSampleTest : TestCase() {

    companion object {
        private const val PHONE_NUMBER = "+79111111111"
        private const val CALL_LOG_UPDATE_DELAY = 750L
        private const val CALL_DURATION = 3_000L
    }

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.READ_CALL_LOG
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun phoneSampleTest() {
        before {
        }.after {
        }.run {

            step("Emulate a call") {
                device.phone.emulateCall(PHONE_NUMBER)
                Screen.idle(CALL_DURATION)
                device.phone.cancelCall(PHONE_NUMBER)
                Screen.idle(CALL_LOG_UPDATE_DELAY)

                assertEquals(PHONE_NUMBER, getLastCallPhoneNumber())
            }
        }
    }

    private fun BaseTestContext.getLastCallPhoneNumber(): String? {
        val cursor = device.targetContext.contentResolver.query(CallLog.Calls.CONTENT_URI, null,
            null, null, CallLog.Calls.DATE + " DESC")

        cursor?.use {
            return if (cursor.moveToFirst()) {
                cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
            } else {
                null
            }
        } ?: return null
    }
}