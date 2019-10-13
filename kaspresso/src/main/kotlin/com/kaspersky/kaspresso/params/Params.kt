package com.kaspersky.kaspresso.params

/**
 * The facade class for all Kaspresso parameters.
 */
data class Params(
    val flakySafetyParams: FlakySafetyParams,
    val checkDuringParams: CheckDuringParams,
    val autoScrollParams: AutoScrollParams,
    val stepParams: StepParams
)