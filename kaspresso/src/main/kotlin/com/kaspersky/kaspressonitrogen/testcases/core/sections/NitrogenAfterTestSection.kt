package com.kaspersky.kaspressonitrogen.testcases.core.sections

import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext
import com.kaspersky.kaspressonitrogen.testcases.models.NitrogenTestBody


class NitrogenAfterTestSection<InitData, Data> internal constructor(
    private val kaspresso: KaspressoNitrogen,
    private val testBodyBuilder: NitrogenTestBody.Builder<InitData, Data>
) {
    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [NitrogenTestBody.afterTestActions].
     *
     * @param actions actions to be wrapped and invoked after the test.
     * @return [NitrogenInitSection] to continue building a test.
     */
    fun after(actions: NitrogenBaseTestContext.() -> Unit): NitrogenInitSection<InitData, Data> {
        return NitrogenMainTestSection(
            kaspresso,
            testBodyBuilder.apply { afterTestActions = actions }
        )
    }
}
