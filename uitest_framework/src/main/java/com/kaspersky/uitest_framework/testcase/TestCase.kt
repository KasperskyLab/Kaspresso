package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.logger.Logger

abstract class TestCase {

    protected fun precondition(description: String, actions: () -> Unit) {
        Logger.i(description)
        actions.invoke()
    }

    protected fun step(description: String, actions: () -> Unit) {
        Logger.i(description)
        actions.invoke()
    }

    open fun before() {}

    open fun after() {}
}