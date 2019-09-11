[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.scenario](../index.md) / [BaseScenario](./index.md)

# BaseScenario

`abstract class BaseScenario<ScenarioData>`

The base class for parametrized scenarios. A representation of some repeating steps inside the
[com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md).

### Parameters

`ScenarioData` - test data created in testcase's before section.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseScenario()`<br>The base class for parametrized scenarios. A representation of some repeating steps inside the [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md). |

### Properties

| Name | Summary |
|---|---|
| [steps](steps.md) | `abstract val steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`ScenarioData`](index.md#ScenarioData)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Steps to run. Need to be implemented in derived [Scenario](../-scenario/index.md). |

### Inheritors

| Name | Summary |
|---|---|
| [Scenario](../-scenario/index.md) | `abstract class Scenario : `[`BaseScenario`](./index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`<br>The base class for scenarios. A representation of some repeating steps inside the [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md). |
