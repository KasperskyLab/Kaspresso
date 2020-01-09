package com.kaspersky.kaspresso.testcases.api.testcaserule

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection
import com.kaspersky.kaspresso.testcases.core.sections.MainTestSection
import com.kaspersky.kaspresso.testcases.core.sections.TransformSection
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.TestBody
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * The base class for all parametrized test cases rules.
 *
 * @param InitData data initialized in before section.
 * @param Data data transformed from [InitData] by special function.
 */
open class BaseTestCaseRule<InitData, Data>(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.advanced(),
    private val testClassName: String,
    private val dataProducer: (((InitData.() -> Unit)?) -> Data),
    private val mainSectionEnrichers: List<MainSectionEnricher<Data>> = emptyList()
) : TestRule {

    private val kaspresso: Kaspresso = kaspressoBuilder.build()

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
        val testBodyBuilder = createBaseTestBodyBuilder(testName)
        return BeforeTestSection(kaspresso, testBodyBuilder).beforeTest(actions)
    }

    /**
     * Starts the building of a test from data initialization section. Sets
     * [com.kaspersky.kaspresso.testcases.core.sections.InitSection] actions and returns an existing instance of
     * [MainTestSection] to continue building a tests.
     *
     * @param testName a name of the test.
     * @param actions actions to be wrapped and invoked before the test for data initialization
     * @return an existing instance of [MainTestSection].
     */
    fun init(
        testName: String = testClassName,
        actions: InitData.() -> Unit
    ): TransformSection<Data> {
        val testBodyBuilder = createBaseTestBodyBuilder(testName).apply {
            initActions = actions
        }
        return MainTestSection(kaspresso, testBodyBuilder)
    }

    /**
     * Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection] steps, creates an instance of
     * [MainTestSection] and runs it
     *
     * @param testName a name of the test.
     * @param steps actions to invoke in main test section.
     */
    fun run(
        testName: String = testClassName,
        steps: TestContext<Data>.() -> Unit
    ) {
        val testBodyBuilder = createBaseTestBodyBuilder(testName)
        MainTestSection(kaspresso, testBodyBuilder).run(steps)
    }

    private fun createBaseTestBodyBuilder(testName: String): TestBody.Builder<InitData, Data> {
        return TestBody.Builder<InitData, Data>().apply {
            this.testName = testName
            this.dataProducer = this@BaseTestCaseRule.dataProducer
            this.mainSectionEnrichers = this@BaseTestCaseRule.mainSectionEnrichers
        }
    }
}