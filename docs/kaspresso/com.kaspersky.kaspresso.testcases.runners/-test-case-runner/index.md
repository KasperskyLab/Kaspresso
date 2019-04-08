[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.runners](../index.md) / [TestCaseRunner](./index.md)

# TestCaseRunner

`class TestCaseRunner : `[`ScenarioRunner`](../-scenario-runner/index.md)

An implementation of [ScenarioRunner](../-scenario-runner/index.md) for [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s usage.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TestCaseRunner(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>An implementation of [ScenarioRunner](../-scenario-runner/index.md) for [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s usage. |

### Functions

| Name | Summary |
|---|---|
| [runSteps](run-steps.md) | `fun runSteps(steps: `[`Scenario`](../../com.kaspersky.kaspresso.testcases/-scenario/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs [beforeTestActions](#), [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.TestCaseRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps) and then runs [afterTestActions](#). [afterTestActions](#) are invoked even if [beforeTestActions](#) or [TestCase](../../com.kaspersky.kaspresso.testcases/-test-case/index.md)'s [steps](run-steps.md#com.kaspersky.kaspresso.testcases.runners.TestCaseRunner$runSteps(kotlin.Function1((com.kaspersky.kaspresso.testcases.Scenario, kotlin.Unit)))/steps) fail. |
