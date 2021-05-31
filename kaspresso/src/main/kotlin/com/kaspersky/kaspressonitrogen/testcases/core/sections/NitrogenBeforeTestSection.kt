package com.kaspersky.kaspressonitrogen.testcases.core.sections

import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext
import com.kaspersky.kaspressonitrogen.testcases.models.NitrogenTestBody
import com.kaspersky.kaspresso.testcases.models.TestBody

class NitrogenBeforeTestSection<InitData, Data> internal constructor(
    private val kaspresso: KaspressoNitrogen,
    private val testBodyBuilder: NitrogenTestBody.Builder<InitData, Data>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [NitrogenTestBody.beforeTestActions].
     *
     * @param actions actions to be wrapped and invoked before the test.
     * @return [NitrogenAfterTestSection] to continue building a test.
     */
    fun beforeTest(actions: NitrogenBaseTestContext.() -> Unit): NitrogenAfterTestSection<InitData, Data> {
        return NitrogenAfterTestSection(
            kaspresso,
            testBodyBuilder.apply { beforeTestActions = actions }
        )
    }
}
