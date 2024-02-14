package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule

import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.data.TestData

import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        stepWatcherInterceptors.addAll(
            listOf(
                ScreenshotStepInterceptor(screenshots),
                AllureMapperStepInterceptor()
            )
        )
        testRunWatcherInterceptors.addAll(
            listOf(
                DumpLogcatTestInterceptor(logcatDumper),
                ScreenshotTestInterceptor(screenshots),
            )
        )
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            val username = TestData.generateUsername()
            val password = TestData.generatePassword()

            testLogger.i("Generated data. Username: $username, Password: $password")

            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = username,
                        password = password
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}
