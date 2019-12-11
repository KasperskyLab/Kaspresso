package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.Context
import android.os.Looper
import androidx.core.os.ConfigurationCompat
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
class DeviceAppLanguageSampleTest : TestCase() {

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
                changeAppLanguageAndCheckIt(Locale.ENGLISH)
            }
            step("Russian language") {
                changeAppLanguageAndCheckIt(Locale("ru"))
            }
            step("English language") {
                changeAppLanguageAndCheckIt(Locale.ENGLISH)
            }
        }
    }

    private fun changeAppLanguageAndCheckIt(locale: Locale) {
        device.language.switchInApp(locale)
        assertEquals(locale.language, getCurrentLocale(device.targetContext))
        updateActivity()
        Thread.sleep(1000)
    }

    private fun getCurrentLocale(context: Context): String =
        ConfigurationCompat.getLocales(context.resources.configuration).get(0).language

    private fun updateActivity() {
        android.os.Handler(Looper.getMainLooper()).post {
            activityTestRule.activity.recreate()
        }
    }
}