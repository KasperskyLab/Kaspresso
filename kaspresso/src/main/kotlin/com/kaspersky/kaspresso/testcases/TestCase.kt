package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.scenarios.Scenario
import com.kaspersky.kaspresso.testcases.scenarios.TestCaseScenario

/**
 *  A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase] as a
 *  parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
 *  exception caused by re-initialization of the [Configurator], use [Scenario] instead.
 */
abstract class TestCase(
        configBuilder: Configurator.Builder = Configurator.Builder.default()
) {
    /**
     * Finishes building of [Configurator]. Passing [Configurator.Builder] to base [TestCase]'s constructor is the only
     * way for project-wide inheritor of [TestCase] to tune [Configurator].
     */
    init {
        configBuilder.commit()
    }

    /**
     *  Creates a [TestCaseRunner] instance, puts actions to invoke before test case's steps in it,
     *  and returns a new instance of [AfterTestHandler] to specify actions to invoke after test
     *  case's steps.
     *
     * @param actionsBefore actions to invoke before test case's steps.
     * @return [AfterTestHandler]
     */
    protected fun beforeTest(actionsBefore: () -> Unit) = AfterTestHandler(
            TestCaseRunner(javaClass.simpleName).apply { this.actionsBefore = actionsBefore }
    )

    /**
     * Specifies actions to invoke after test case's steps.
     */
    protected class AfterTestHandler(private val runner: TestCaseRunner) {

        /**
         * Puts [actionsAfter] in [TestCaseRunner] and returns it.
         *
         * @param actionsAfter actions to invoke after test case's steps.
         * @return [TestCaseRunner]
         */
        fun afterTest(actionsAfter: () -> Unit) = runner.apply { this.actionsAfter = actionsAfter }
    }

    /**
     * An implementation of [ScenarioRunner] for [TestCase]'s usage.
     */
    protected class TestCaseRunner(
            private val title: String
    ) : ScenarioRunner {

        internal lateinit var actionsBefore: () -> Unit
        internal lateinit var actionsAfter: () -> Unit

        /**
         * Runs [actionsBefore], [TestCase]'s steps and then [actionsAfter]. [actionsAfter] are
         * invoked even if [actionsBefore] or [TestCase]'s steps fail.
         *
         * @param steps the steps to run.
         */
        override fun runSteps(steps: Scenario.() -> Unit) {
            try {
                actionsBefore.invoke()
                steps.invoke(TestCaseScenario(title))
            } finally {
                actionsAfter.invoke()
            }
        }
    }
}