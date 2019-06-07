package com.kaspersky.kaspresso.testcases.api

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.sections.BeforeTestSection
import com.agoda.kakao.configurator.KakaoConfigurator
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 *  A base class for all parametrized test cases rules.
 *
 *  @param BeforeSectionData data initialized in before section
 *  @param MainSectionData data transformed from [BeforeSectionData] by special function
 */
open class BaseTestCaseRule<BeforeSectionData, MainSectionData>(
    private val testClassName: String,
    private val configBuilder: Configurator.Builder = Configurator.Builder.default(),
    private val dataProducer: (((BeforeSectionData.() -> Unit)?) -> MainSectionData)
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
     * @param testName a name of the test
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    fun before(testName: String = testClassName, actions: BaseTestContext.() -> Unit): AfterTestSection<BeforeSectionData, MainSectionData> {
        return BeforeTestSection(
            configurator,
            TestBody.Builder<BeforeSectionData, MainSectionData>().apply {
                this.testName = testName
                mainDataProducer = dataProducer
            }
        ).beforeTest(actions)
    }

}