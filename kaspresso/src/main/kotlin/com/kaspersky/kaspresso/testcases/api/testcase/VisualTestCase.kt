package com.kaspersky.kaspresso.testcases.api.testcase


import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

abstract class VisualTestCase(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.simple(),
) : TestCase(kaspressoBuilder) {
    fun runScreenshotTest(
        before: (BaseTestContext.() -> Unit)? = null,
        after: (BaseTestContext.() -> Unit)? = null,
        test: TestContext<Unit>.() -> Unit,
    ) = before {
        kaspresso.visualTestWatcher.prepare()
        before?.invoke(this)
    }.after {
        kaspresso.visualTestWatcher.cleanUp()
        after?.invoke(this)
    }.run(test)

    fun assertScreenshot(tag: String) {
        device.screenshots.assert(tag)
    }
}
