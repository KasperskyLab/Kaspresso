[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.runners](../index.md) / [SubCaseRunner](index.md) / [runSteps](./run-steps.md)

# runSteps

`fun runSteps(steps: `[`Scenario`](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [ScenarioRunner.runSteps](../-scenario-runner/run-steps.md)

Runs [SubCase](../../com.kaspersky.kaspresso.testcases/-sub-case/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.SubCaseRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps). Called by [SubCase.run](../../com.kaspersky.kaspresso.testcases/-sub-case/run.md) with [SubCase.steps](../../com.kaspersky.kaspresso.testcases/-sub-case/steps.md) as an argument.

### Parameters

`steps` - steps to run.