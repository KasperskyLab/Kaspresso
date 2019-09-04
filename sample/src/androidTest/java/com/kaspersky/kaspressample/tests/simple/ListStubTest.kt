package com.kaspersky.kaspressample.tests.simple

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.ListStubScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListStubTest : TestCase() {

    private val mainScreen = MainScreen()
    private val listStubScreen = ListStubScreen()

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {
            step("Open List Stub Screen") {
                mainScreen {
                    listStubButton {
                        click()
                    }
                }
            }

            step("Find 56th element") {
                listStubScreen {
                    listStub {
                        isVisible()
                        hasSize(100)

                        childWith<ListStubScreen.StubItem> {
                            isInstanceOf(String::class.java)
                            equals("56")
                        } perform {
                            isVisible()
                        }
                    }
                }
            }
        }
    }
}