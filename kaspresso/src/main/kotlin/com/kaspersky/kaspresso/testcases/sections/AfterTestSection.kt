package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody

/**
 * A representation of a set of actions to invoke after the test.
 */
class AfterTestSection<BeforeSectionData, MainSectionData>(
    private val configurator: Configurator,
    private val builder: TestBody.Builder<BeforeSectionData, MainSectionData>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [MainTestSection.afterTestActions] to [runner].
     *
     * @param actions actions to be wrapped and invoked after the test.
     * @return [runner] to continue building a test.
     */
    fun after(actions: BaseTestContext.() -> Unit): InitialisableMainSection<BeforeSectionData, MainSectionData> {
        return MainTestSection(
            configurator,
            builder.apply { afterTestSection = actions }
        )
    }
}