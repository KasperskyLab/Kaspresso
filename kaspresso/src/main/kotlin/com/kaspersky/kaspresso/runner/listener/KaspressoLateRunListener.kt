package com.kaspersky.kaspresso.runner.listener

import org.junit.runner.Description

interface KaspressoLateRunListener : KaspressoRunListener {

    fun lateInit(cache: Cache) {
        testRunStarted(cache.testRunStartedDescription)
        testStarted(cache.firstTestStartedDescription)
    }

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
