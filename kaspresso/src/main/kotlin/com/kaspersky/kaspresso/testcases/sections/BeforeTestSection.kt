package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.testcases.models.TestBody

/**
 * A representation of a set of actions to be invoked before the test.
 */
class BeforeTestSection<BeforeSectionData, MainSectionData>(
    private val builder: TestBody.Builder<BeforeSectionData, MainSectionData>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [MainTestSection.beforeTestActions] to [runner].
     *
     * @param actions actions to be wrapped and invoked before the test.
     */
    fun beforeTest(actions: () -> Unit): AfterTestSection <BeforeSectionData, MainSectionData>{
        return AfterTestSection(
            builder.apply { beforeTestSection = actions }
        )
    }
}