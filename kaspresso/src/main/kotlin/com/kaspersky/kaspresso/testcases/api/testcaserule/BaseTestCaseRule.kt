package com.kaspersky.kaspresso.testcases.api.testcaserule

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 *  A base class for all parametrized test cases rules.
 *
 *  @param InitData data initialized in before section.
 *  @param Data data transformed from [InitData] by special function.
 */
open class BaseTestCaseRule<InitData, Data>(
    configBuilder: Configurator.Builder = Configurator.Builder.default(),
    private val dataProducer: (((InitData.() -> Unit)?) -> Data),
    private val testClassName: String
) : TestRule {

    private val configurator: Configurator = configBuilder.build()

    override fun apply(base: Statement?, description: Description?) = object : Statement() {
        override fun evaluate() {
            requireNotNull(base).evaluate()
        }
    }

    /**
     * Starts the building a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue building a test.
     *
     * @param testName a name of the test.
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    fun before(
        testName: String = testClassName,
        actions: BaseTestContext.() -> Unit
    ): AfterTestSection<InitData, Data> {

        val testBodyBuilder = TestBody.Builder<InitData, Data>().apply {
            this.testName = testName
            this.dataProducer = this@BaseTestCaseRule.dataProducer
        }

        return BeforeTestSection(configurator, testBodyBuilder).beforeTest(actions)
    }
}