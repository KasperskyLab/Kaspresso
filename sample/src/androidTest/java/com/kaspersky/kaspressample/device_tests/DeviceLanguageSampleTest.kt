package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.Context
import android.os.Looper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceLanguageSampleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun languageSwitcherTest() {
        run {
            step("English language") {
                device.languageSwitcher.switchLanguageInApp(Locale.ENGLISH)
                assertEquals(Locale.ENGLISH.language, getCurrentLocale(device.targetContext))
                updateActivity()
                Thread.sleep(1000)
            }
            step("Russian language") {
                device.languageSwitcher.switchLanguageInApp(Locale("ru"))
                assertEquals(Locale("ru").language, getCurrentLocale(device.targetContext))
                updateActivity()
                Thread.sleep(1000)
            }
            step("English language") {
                device.languageSwitcher.switchLanguageInApp(Locale.ENGLISH)
                assertEquals(Locale.ENGLISH.language, getCurrentLocale(device.targetContext))
                updateActivity()
                Thread.sleep(1000)
            }
        }
    }

    private fun getCurrentLocale(context: Context): String =
        context.resources.configuration.locale.language

    private fun updateActivity() {
        android.os.Handler(Looper.getMainLooper()).post {
            activityTestRule.activity.recreate()
        }
    }
}