package com.kaspersky.kaspressample.dsl_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class InitTransformDataTest : BaseParametrizedTest() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = init {
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
        step("Some test step") {
            testLogger.i(data.companies.toString())

            MainScreen {
                descriptionText {
                    hasNoText(data.owners.first().firstName ?: "")
                }

                simpleButton {
                    click()
                }
            }
        }
    }
}
