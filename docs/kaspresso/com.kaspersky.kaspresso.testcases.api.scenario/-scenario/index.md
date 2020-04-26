[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.scenario](../index.md) / [Scenario](./index.md)

# Scenario

`abstract class Scenario : `[`BaseScenario`](../-base-scenario/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`

The base class for scenarios. A representation of some repeating steps inside the
[com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The base class for scenarios. A representation of some repeating steps inside the [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md).`Scenario()` |

### Properties

| Name | Summary |
|---|---|
| [steps](steps.md) | Steps to run. Need to be implemented in derived [Scenario](./index.md).`abstract val steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
