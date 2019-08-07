package com.kaspersky.kaspressample.tests.paramertized

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.scenarios.CheckHomeTitleNoParametersScenario
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.viewactions.orientation.Orientation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenHomeScreenParametrizedTest : BaseParametrizedTest() {

    private val mainScreen = MainScreen()

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
            device.exploit.setOrientation(Orientation.Portrait)
        }.init {
            company {
                name = "Microsoft"
                city = "Redmond"
                country = "USA"
            }
            company {
                name = "Google"
                city = "Mountain View"
                country = "USA"
            }
            owner {
                firstName = "Satya"
                secondName = "Nadella"
                country = "India"
            }
            owner {
                firstName = "Sundar"
                secondName = "Pichai"
                country = "India"
            }
        }.transform {
            makeOwner(ownerSurname = "Nadella", companyName = "Microsoft")
            makeOwner(ownerSurname = "Pichai", companyName = "Google")
        }.run {
            step("Open Home Screen") {
                kLogger.i(data.companies.toString())

                mainScreen {
                    descriptionText {
                        // Oops, it fails, please comment if you want to pass the test
                        hasText(data.owners.first().firstName ?: "")
                    }

                    nextButton {
                        click()
                    }
                }
            }

            scenario(CheckHomeTitleNoParametersScenario())
        }
    }
}