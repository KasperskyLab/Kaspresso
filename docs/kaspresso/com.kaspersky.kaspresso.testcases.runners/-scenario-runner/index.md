[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.runners](../index.md) / [ScenarioRunner](./index.md)

# ScenarioRunner

`interface ScenarioRunner`

An interface to run [Scenario](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)'s steps.

### Functions

| Name | Summary |
|---|---|
| [runSteps](run-steps.md) | `abstract fun runSteps(steps: `[`Scenario`](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs the [Scenario](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.ScenarioRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps). |

### Inheritors

| Name | Summary |
|---|---|
| [SubCaseRunner](../-sub-case-runner/index.md) | `class SubCaseRunner : `[`ScenarioRunner`](./index.md)<br>An implementation of [ScenarioRunner](./index.md) for [SubCase](../../com.kaspersky.kaspresso.testcases/-sub-case/index.md)'s usage. |
| [TestCaseRunner](../-test-case-runner/index.md) | `class TestCaseRunner : `[`ScenarioRunner`](./index.md)<br>An implementation of [ScenarioRunner](./index.md) for [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s usage. |
