package com.kaspersky.kaspresso.testcases.api

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.sections.BeforeTestSection

/**
 *  A base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of
 *  [TestCase] as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may
 *  produce an exception caused by re-initialization of the [Configurator], use [Scenario] instead.
 *
 *  @param BeforeSectionData data initialized in before section.
 *  @param MainSectionData data transformed from [BeforeSectionData] by special function.
 */
abstract class BaseTestCase<BeforeSectionData, MainSectionData>(
    configBuilder: Configurator.Builder = Configurator.Builder.default(),
    private val dataProducer: (((BeforeSectionData.() -> Unit)?) -> MainSectionData)
) {
    private val testCaseName = javaClass.simpleName

    internal val configurator: Configurator = configBuilder.build()

    /**
     * Starts the building of a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue the building a test.
     *
     * @param testName a name of the test.
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    protected fun before(
        testName: String = testCaseName,
        actions: BaseTestContext.() -> Unit
    ): AfterTestSection<BeforeSectionData, MainSectionData> {

        return BeforeTestSection(
            configurator,
            TestBody.Builder<BeforeSectionData, MainSectionData>().apply {
                this.testName = testName
                mainDataProducer = dataProducer
            }
        ).beforeTest(actions)
    }
}