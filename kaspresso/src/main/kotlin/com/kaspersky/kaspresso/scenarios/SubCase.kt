package com.kaspersky.kaspresso.scenarios

import com.kaspersky.kaspresso.scenarios.stepsrunners.SubCaseStepsRunner


/**
 * A base class for all subcases.
 */
abstract class SubCase : Scenario<SubCaseStepsRunner>(
    stepsRunnerFactory = { testCaseName -> SubCaseStepsRunner(testCaseName) }
)
