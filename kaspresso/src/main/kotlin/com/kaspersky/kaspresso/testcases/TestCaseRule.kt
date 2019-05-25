package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.sections.BeforeTestSection
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCaseRule(
    val context: Any,
    val configBuilder: Configurator.Builder = Configurator.Builder.default()
) : TestRule {
    private val testCaseName = javaClass.simpleName

    override fun apply(base: Statement?, description: Description?) = object : Statement() {
        override fun evaluate() {
            configBuilder.commit()
            base?.evaluate() ?: throw RuntimeException(
                "It's so unbelievably! I don't know why but TestCaseRule was broken with nullable base: Statement? argument. " +
                        "Try TestCase simple abstract class (example - OpenHomeScreenTest)"
            )
        }
    }

    /**
     * Starts the building a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue building a test.
     *
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    fun beforeTest(actions: () -> Unit): AfterTestSection {
        return BeforeTestSection(
            TestBody.Builder().apply { testName = testCaseName }
        ).beforeTest(actions)
    }

}