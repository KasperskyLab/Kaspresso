package com.kaspersky.kaspressample.utils

import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert

object SafeAssert {

    fun BaseTestContext.assertTrueSafely(
        failureMessage: String? = null,
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        condition: () -> Boolean
    ) {
        flakySafely(
            timeoutMs = timeoutMs,
            intervalMs = intervalMs,
            failureMessage = failureMessage
        ) {
            Assert.assertTrue(condition.invoke())
        }
    }

    fun BaseTestContext.assertFalseSafely(
        failureMessage: String? = null,
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        condition: () -> Boolean
    ) {
        flakySafely(
            timeoutMs = timeoutMs,
            intervalMs = intervalMs,
            failureMessage = failureMessage
        ) {
            Assert.assertFalse(condition.invoke())
        }
    }
}
