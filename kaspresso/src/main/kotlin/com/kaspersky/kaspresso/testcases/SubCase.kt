package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.core.Scenario
import com.kaspersky.kaspresso.testcases.core.TestBody
import com.kaspersky.kaspresso.testcases.models.TestInfo

/**
 * A base class for all subcases. A representation of some repeating scenario inside the [TestCase].
 */
abstract class SubCase {

    private val info = TestInfo(javaClass.simpleName)

    /**
     * Steps to run. Need to be implemented in derived [SubCase].
     */
    protected abstract val steps: Scenario.() -> Unit

    fun run() {
        TestBody.Builder()
            .apply {
                testInfo = info
                beforeTestSection = { Unit }
                afterTestSection = { Unit }
                mainTestSection = { steps.invoke(Scenario(info)) }
            }
            .build()
            .run()
    }
}