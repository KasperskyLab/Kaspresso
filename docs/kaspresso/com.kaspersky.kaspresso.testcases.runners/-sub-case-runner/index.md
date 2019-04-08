[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.runners](../index.md) / [SubCaseRunner](./index.md)

# SubCaseRunner

`class SubCaseRunner : `[`ScenarioRunner`](../-scenario-runner/index.md)

An implementation of [ScenarioRunner](../-scenario-runner/index.md) for [SubCase](../../com.kaspersky.kaspresso.testcases/-sub-case/index.md)'s usage.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SubCaseRunner(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>An implementation of [ScenarioRunner](../-scenario-runner/index.md) for [SubCase](../../com.kaspersky.kaspresso.testcases/-sub-case/index.md)'s usage. |

### Functions

| Name | Summary |
|---|---|
| [runSteps](run-steps.md) | `fun runSteps(steps: `[`Scenario`](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs [SubCase](../../com.kaspersky.kaspresso.testcases/-sub-case/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.SubCaseRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps). Called by [SubCase.run](../../com.kaspersky.kaspresso.testcases/-sub-case/run.md) with [SubCase.steps](../../com.kaspersky.kaspresso.testcases/-sub-case/steps.md) as an argument. |
