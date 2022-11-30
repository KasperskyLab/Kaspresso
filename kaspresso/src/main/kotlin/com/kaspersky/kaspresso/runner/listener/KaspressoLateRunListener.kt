package com.kaspersky.kaspresso.runner.listener

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
    fun lateInit(cache: KaspressoRunNotifier.Cache) {
        testRunStarted(cache.testRunStartedDescription)
        testStarted(cache.firstTestStartedDescription)
    }
}
