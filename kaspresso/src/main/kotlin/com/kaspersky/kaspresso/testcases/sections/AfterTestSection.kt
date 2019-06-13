package com.kaspersky.kaspresso.testcases.sections

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.core.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody

/**
 * A representation of a set of actions to invoke after the test.
 */
class AfterTestSection<InitData, Data> internal constructor(
    private val configurator: Configurator,
    private val testBodyBuilder: TestBody.Builder<InitData, Data>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [TestBody.afterTestActions].
     *
     * @param actions actions to be wrapped and invoked after the test.
     * @return [InitSection] to continue building a test.
     */
    fun after(actions: BaseTestContext.() -> Unit): InitSection<InitData, Data> {
        return MainTestSection(
            configurator,
            testBodyBuilder.apply { afterTestActions = actions }
        )
    }
}