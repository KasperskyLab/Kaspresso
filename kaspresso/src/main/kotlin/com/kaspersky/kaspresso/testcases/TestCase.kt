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
     * An instance of a [TestCaseRunner] that executes the test with it's [BeforeTestSection] and [AfterTestSection].
     */
    private val runner = TestCaseRunner(javaClass.simpleName)

    /**
     * An instance of [BeforeTestSection] to specify the actions to be invoked before test.
     */
    private val beforeTestSection = BeforeTestSection(runner)

    /**
     * An instance of [AfterTestSection] to specify the actions to be invoked after test.
     */
    private val afterTestSection = AfterTestSection(runner)

    /**
     * Finishes building of [Configurator]. Passing [Configurator.Builder] to base [TestCase]'s constructor is the only
     * way for project-wide inheritor of [TestCase] to tune [Configurator].
     */
    init {
        configBuilder.commit()
    }

    /**
     * Starts the building a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue building a test.
     *
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    protected fun beforeTest(actions: () -> Unit): AfterTestSection {
        beforeTestSection.beforeTest(actions)
        return afterTestSection
    }
}