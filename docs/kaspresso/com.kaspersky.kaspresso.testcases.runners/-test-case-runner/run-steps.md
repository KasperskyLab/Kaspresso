[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.runners](../index.md) / [TestCaseRunner](index.md) / [runSteps](./run-steps.md)

# runSteps

`fun runSteps(steps: `[`Scenario`](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [ScenarioRunner.runSteps](../-scenario-runner/run-steps.md)

Runs [beforeTestActions](#), [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.TestCaseRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps) and then runs [afterTestActions](#). [afterTestActions](#) are invoked
even if [beforeTestActions](#) or [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.TestCaseRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps) fail.

### Parameters

`steps` - steps to run.