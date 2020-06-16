package com.kaspersky.kaspresso.steps

import com.kaspersky.kaspresso.testcases.models.TestIdentifier

/**
 * Interface for consumers of steps results information.
 */
interface StepsResultsConsumer {

    /**
     * Method to send information about steps in JSON format into consumer to propagate it further correctly.
     */
    fun consume(testIdentifier: TestIdentifier, stepsResultsJson: String)
}