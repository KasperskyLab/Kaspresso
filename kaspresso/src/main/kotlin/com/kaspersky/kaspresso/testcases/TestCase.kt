package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.runners.TestCaseRunner

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
     * @return a new instance of [AfterTestHandler].
     */
    protected fun beforeTest(actionsBefore: () -> Unit) = AfterTestHandler(
            TestCaseRunner(javaClass.simpleName).apply { this.actionsBefore = actionsBefore }
    )

    /**
     * Specifies actions to invoke after test case's steps.
     */
    protected class AfterTestHandler(private val runner: TestCaseRunner) {

        /**
         * Puts [actionsAfter] in the instance of the [TestCaseRunner] and returns it.
         *
         * @param actionsAfter actions to invoke after test case's steps.
         * @return existing instance of [TestCaseRunner].
         */
        fun afterTest(actionsAfter: () -> Unit) = runner.apply { this.actionsAfter = actionsAfter }
    }
}