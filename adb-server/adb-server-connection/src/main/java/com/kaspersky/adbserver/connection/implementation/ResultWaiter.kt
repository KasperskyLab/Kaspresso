package com.kaspersky.adbserver.connection.implementation

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * The waiter of a result
 */
internal class ResultWaiter<Result> {

    @Volatile
    private var result: Result? = null
    private val waitLatch = CountDownLatch(1)

    fun latchResult(result: Result) {
        this.result = result
        waitLatch.countDown()
    }

    @Throws(InterruptedException::class)
    fun waitResult(timeout: Long, unit: TimeUnit): Result? {
        waitLatch.await(timeout, unit)
        return result
    }
}
