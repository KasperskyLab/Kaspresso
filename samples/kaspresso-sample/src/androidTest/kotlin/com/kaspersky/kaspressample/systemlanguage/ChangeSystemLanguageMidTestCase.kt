package com.kaspersky.kaspressample.systemlanguage

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.ChangeLocaleScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.docloc.SystemLanguage
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import java.util.Locale

class ChangeSystemLanguageMidTestCase : TestCase() {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkLocaleChangeMidTest() = run {
        val systemLanguage = SystemLanguage(device.targetContext, testLogger, device.hackPermissions)
        step("Open change locale screen") {
            MainScreen {
                changeLocaleButton { click() }
            }
        }
        step("Check EN locale text") {
            systemLanguage.switch(Locale.ENGLISH)
            ChangeLocaleScreen {
                text { containsText("123") }
            }
        }
        step("Check RU locale text") {
            systemLanguage.switch(Locale.forLanguageTag("RU"))
            ChangeLocaleScreen {
                text { containsText("321") }
            }
        }
    }
}
