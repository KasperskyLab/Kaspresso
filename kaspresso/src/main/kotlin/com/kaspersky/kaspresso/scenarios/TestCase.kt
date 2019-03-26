package com.kaspersky.kaspresso.scenarios

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.scenarios.stepsrunners.TestCaseStepsRunner

/**
 *  A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase] as a
 *  parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
 *  exception caused by re-initialization of the [Configurator], use [Scenario] instead.
 */
abstract class TestCase (
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) {

    /**
     * Finishes building of [Configurator]. Passing [Configurator.Builder] to base [TestCase]'s constructor is the only
     * way for project-wide inheritor of [TestCase] to tune [Configurator].
     */
    init {
        configBuilder.commit()
    }

    protected fun beforeTest(actionsBefore: () -> Unit): BeforeTest {
        return BeforeTest(actionsBefore)
    }

    protected inner class BeforeTest(private val actionsBefore: () -> Unit) {

        fun afterTest(actionsAfter: () -> Unit): AfterTest {
            return AfterTest(actionsBefore, actionsAfter)
        }

    }

    protected inner class AfterTest(
        private val actionsBefore: () -> Unit,
        private val actionsAfter: () -> Unit
    ) {

        fun runSteps(steps: TestCaseStepsRunner.() -> Unit) {
            val stepsRunner = TestCaseStepsRunner(javaClass.simpleName)
            stepsRunner.stepsCounter = 0
            actionsBefore.invoke()
            try {
                steps.invoke(stepsRunner)
            } finally {
                actionsAfter.invoke()
            }
        }

    }

}
