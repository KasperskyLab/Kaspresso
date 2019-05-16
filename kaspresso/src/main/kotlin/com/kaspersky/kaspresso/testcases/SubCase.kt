package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.core.StepContext
import com.kaspersky.kaspresso.testcases.models.TestInfo

/**
 * A base class for all subcases. A representation of some repeating scenario inside the [TestCase].
 */
abstract class SubCase {

    /**
     * Steps to run. Need to be implemented in derived [SubCase].
     */
    protected abstract val steps: StepContext.() -> Unit

    fun run(stepContext: StepContext){
        steps.invoke(stepContext)
    }
}