package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.provider.CallLog
import android.provider.Telephony
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class DevicePhoneSampleTest : TestCase() {

    companion object {
        private const val PHONE_NUMBER = "+79111111111"
        private const val SMS_MESSAGE_TEXT = "Kaspresso"
        private const val CONTENT_UPDATE_DELAY = 5_000L
        private const val CALL_DURATION = 2_000L
    }

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_SMS,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun phoneSampleTest() {
        run {

            step("Emulate a call") {
                device.phone.emulateCall(PHONE_NUMBER)
                Screen.idle(CALL_DURATION)
                device.phone.cancelCall(PHONE_NUMBER)
                Screen.idle(CONTENT_UPDATE_DELAY)

                assertEquals(PHONE_NUMBER, getLastCallPhoneNumber())
            }

            step("Receive SMS message") {
                device.phone.receiveSms(
                    PHONE_NUMBER,
                    SMS_MESSAGE_TEXT
                )
                Screen.idle(CONTENT_UPDATE_DELAY)

                val messageInfo = getLastSmsInfo()
                assertNotNull(messageInfo)
                assertEquals(PHONE_NUMBER, messageInfo?.address)
                assertEquals(SMS_MESSAGE_TEXT, messageInfo?.message)
            }
        }
    }

    private fun BaseTestContext.getLastCallPhoneNumber(): String? {
        val cursor = device.targetContext.contentResolver.query(
            CallLog.Calls.CONTENT_URI, null,
            null, null, CallLog.Calls.DATE + " DESC"
        )

        cursor?.use {
            return if (cursor.moveToFirst()) {
                cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
            } else {
                null
            }
        } ?: return null
    }

    private fun BaseTestContext.getLastSmsInfo(): SmsInfo? {
        val cursor = device.targetContext.contentResolver.query(
            Telephony.Sms.Inbox.CONTENT_URI,
            arrayOf(Telephony.TextBasedSmsColumns.ADDRESS, Telephony.TextBasedSmsColumns.BODY),
            null,
            null,
            Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
        )

        cursor?.use {
            return if (cursor.moveToFirst()) {
                val address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.TextBasedSmsColumns.ADDRESS))
                val message = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.TextBasedSmsColumns.BODY))
                SmsInfo(address, message)
            } else {
                null
            }
        } ?: return null
    }

    private data class SmsInfo(val address: String, val message: String)
}
