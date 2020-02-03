package com.kaspersky.kaspresso.params

/**
 * The facade class for all Kaspresso parameters.
 */
data class Params(
    val flakySafetyParams: FlakySafetyParams,
    val kautomatorFlakySafetyParams: FlakySafetyParams,
    val continuouslyParams: ContinuouslyParams,
    val kautomatorContinuouslyParams: ContinuouslyParams,
    val autoScrollParams: AutoScrollParams,
    val kautomatorAutoScrollParams: AutoScrollParams,
    val stepParams: StepParams,
    val waitForIdleParams: WaitForIdleParams
)