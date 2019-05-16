package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.runners.TestCaseRunner
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCaseRule(
    val configBuilder: Configurator.Builder = Configurator.Builder.default()
) : TestRule {

    override fun apply(base: Statement?, description: Description?) = object : Statement() {
        override fun evaluate() {
            configBuilder.commit()
            base!!.evaluate()
        }
    }

    /**
     * Starts the building a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue building a test.
     *
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    fun beforeTest(actions: () -> Unit) = createBeforeTestSection().beforeTest(actions)

    /**
     * Creates an instance of [BeforeTestSection] with a new instance of [TestCaseRunner] as a parameter.
     */
    private fun createBeforeTestSection() = BeforeTestSection(TestCaseRunner(javaClass.simpleName))

}