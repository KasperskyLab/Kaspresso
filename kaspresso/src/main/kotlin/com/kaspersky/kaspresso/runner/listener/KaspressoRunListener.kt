package com.kaspersky.kaspresso.runner.listener

import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure

interface KaspressoRunListener {
    fun testRunStarted(description: Description) = Unit
    fun testStarted(description: Description) = Unit
    fun testFinished(description: Description) = Unit
    fun testFailure(failure: Failure) = Unit
    fun testAssumptionFailure(failure: Failure) = Unit
    fun testIgnored(description: Description) = Unit
    fun testRunFinished(result: Result) = Unit
}
