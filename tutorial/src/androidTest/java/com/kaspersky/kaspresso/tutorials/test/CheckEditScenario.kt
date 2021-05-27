package com.kaspersky.kaspresso.tutorials.test

import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.tutorials.screen.SimpleScreen

class CheckEditScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {

        step("Change the text in edit and check it") {
            SimpleScreen {
                edit {
                    clearText()
                    typeText("111")
                    hasText("111")
                }
            }
        }

        step("Change the text in edit and check it. Second check") {
            SimpleScreen {
                edit {
                    clearText()
                    typeText("222")
                    hasText("222")
                }
            }
        }
    }
}
