package com.kaspersky.kaspresso.testcases.core.sections

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody

/**
 * The representation of a set of actions to be invoked before the test.
 */
class BeforeTestSection<InitData, Data> internal constructor(
    private val kaspresso: Kaspresso,
    private val testBodyBuilder: TestBody.Builder<InitData, Data>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [TestBody.beforeTestActions].
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [AfterTestSection] to continue building a test.
     */
    fun beforeTest(actions: BaseTestContext.() -> Unit): AfterTestSection<InitData, Data> {
        return AfterTestSection(
            kaspresso,
            testBodyBuilder.apply { beforeTestActions = actions }
        )
    }
}
