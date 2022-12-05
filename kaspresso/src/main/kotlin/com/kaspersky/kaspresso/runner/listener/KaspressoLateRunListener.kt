package com.kaspersky.kaspresso.runner.listener

import org.junit.runner.Description

/**
 * Listener added in runtime. When test tun listeners are added in runtime it won't
 * call testRunStarted and testStarted because it was added to late. Thus it's called
 * late (it's added in runtime, later than test was started) and it calls testRunStarted
 * and testStarted in lateInit method.
 *
 * Currently it used only for Allure tests
 */
interface KaspressoLateRunListener : KaspressoRunListener {

    /**
     * Call this after KaspressoLateRunListener was added to test runner to guarantee
     * the correct callbacks call order
     *
     * @param cache - callbacks that were called before KaspressoLateRunListener was added to runner
     */
    fun lateInit(cache: Cache) {
        testRunStarted(cache.testRunStartedDescription)
        testStarted(cache.firstTestStartedDescription)
    }

    /**
     * "Late" listeners don't receive first testRunStarted and testStarted calls.
     * This cache is used to store the first test and the first test run descriptions
     * to pass them into late listeners later
     */
    class Cache {
        lateinit var testRunStartedDescription: Description
            private set

        lateinit var firstTestStartedDescription: Description
            private set

        fun testRunStarted(description: Description) {
            if (!::testRunStartedDescription.isInitialized) {
                testRunStartedDescription = description
            }
        }

        fun testStarted(description: Description) {
            if (!::firstTestStartedDescription.isInitialized) {
                firstTestStartedDescription = description
            }
        }
    }
}
