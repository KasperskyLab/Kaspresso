package com.kaspersky.kaspresso.runner.listener

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

internal class SpyRunListener : RunListener() {

    private val runNotifier = InstrumentationRegistry.getInstrumentation().asKaspressoRunNotifier()

    override fun testRunStarted(description: Description) {
        runNotifier.testRunStarted(description)
    }

    override fun testStarted(description: Description) {
        runNotifier.testStarted(description)
    }

    override fun testFinished(description: Description) {
        runNotifier.testFinished(description)
    }

    override fun testFailure(failure: Failure) {
        runNotifier.testFailure(failure)
    }

    override fun testAssumptionFailure(failure: Failure) {
        runNotifier.testAssumptionFailure(failure)
    }

    override fun testIgnored(description: Description) {
        runNotifier.testIgnored(description)
    }

    override fun testRunFinished(result: Result) {
        runNotifier.testRunFinished(result)
    }
}
