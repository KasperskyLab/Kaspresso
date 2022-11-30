package com.kaspersky.kaspresso.runner.listener

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

/**
 * Used to inject kaspresso notifier into test runner. It receives test progress and
 * passes it into run notifier
 */
internal class SpyRunListener : RunListener() {

    private val runNotifier = InstrumentationRegistry.getInstrumentation().getKaspressoRunNotifier()

    /**
     * Called before any tests have been run. This may be called on an
     * arbitrary thread.
     *
     * @param description describes the tests to be run
     */
    override fun testRunStarted(description: Description) {
        runNotifier.testRunStarted(description)
    }

    /**
     * Called when an atomic test is about to be started.
     *
     * @param description the description of the test that is about to be run
     * (generally a class and method name)
     */
    override fun testStarted(description: Description) {
        runNotifier.testStarted(description)
    }

    /**
     * Called when an atomic test has finished, whether the test succeeds or fails.
     *
     * @param description the description of the test that just ran
     */
    override fun testFinished(description: Description) {
        runNotifier.testFinished(description)
    }

    /**
     * Called when an atomic test fails, or when a listener throws an exception.
     *
     * <p>In the case of a failure of an atomic test, this method will be called
     * with the same {@code Description} passed to
     * {@link #testStarted(Description)}, from the same thread that called
     * {@link #testStarted(Description)}.
     *
     * <p>In the case of a listener throwing an exception, this will be called with
     * a {@code Description} of {@link Description#TEST_MECHANISM}, and may be called
     * on an arbitrary thread.
     *
     * @param failure describes the test that failed and the exception that was thrown
     */
    override fun testFailure(failure: Failure) {
        runNotifier.testFailure(failure)
    }

    /**
     * Called when an atomic test flags that it assumes a condition that is
     * false
     *
     * @param failure describes the test that failed and the
     * {@link org.junit.AssumptionViolatedException} that was thrown
     */
    override fun testAssumptionFailure(failure: Failure) {
        runNotifier.testAssumptionFailure(failure)
    }

    /**
     * Called when a test will not be run, generally because a test method is annotated
     * with {@link org.junit.Ignore}.
     *
     * @param description describes the test that will not be run
     */
    override fun testIgnored(description: Description) {
        runNotifier.testIgnored(description)
    }

    /**
     * Called when all tests have finished. This may be called on an
     * arbitrary thread.
     *
     * @param result the summary of the test run, including all the tests that failed
     */
    override fun testRunFinished(result: Result) {
        runNotifier.testRunFinished(result)
    }
}
