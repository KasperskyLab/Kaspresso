package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody

/**
 * A representation of a set of actions to be invoked before the test.
 */
class BeforeTestSection<BeforeSectionData, MainSectionData>(
    private val configurator: Configurator,
    private val builder: TestBody.Builder<BeforeSectionData, MainSectionData>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [TestBody.beforeTestActions].
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [AfterTestSection] to continue building a test.
     */
    fun beforeTest(
        actions: BaseTestContext.() -> Unit
    ): AfterTestSection<BeforeSectionData, MainSectionData> {

        return AfterTestSection(
            configurator,
            builder.apply { beforeTestSection = actions }
        )
    }
}