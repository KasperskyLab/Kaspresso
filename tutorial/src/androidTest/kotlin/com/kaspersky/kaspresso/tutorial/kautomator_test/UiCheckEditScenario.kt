package com.kaspersky.kaspresso.tutorial.kautomator_test

import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.tutorial.kautomator_screen.UiSimpleScreen

class UiCheckEditScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {

        step("Change the text in edit and check it") {
            UiSimpleScreen {
                edit {
                    clearText()
                    typeText("111")
                    hasText("111")
                }
            }
        }

        step("Change the text in edit and check it. Second check") {
            UiSimpleScreen {
                edit {
                    clearText()
                    typeText("222")
                    hasText("222")
                }
            }
        }
    }
}
