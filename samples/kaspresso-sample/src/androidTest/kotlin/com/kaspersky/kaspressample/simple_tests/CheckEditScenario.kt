package com.kaspersky.kaspressample.simple_tests

import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

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
