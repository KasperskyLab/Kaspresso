[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.testcontext](../index.md) / [TestContext](./index.md)

# TestContext

`class TestContext<Data> : `[`BaseTestContext`](../-base-test-context.md)

The special class to operate with in user scenario.
Provides [step](step.md) and [scenario](scenario.md) methods in "run" section to build a test.

### Parameters

`Data` - data created in before section.

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: Data` |

### Functions

| Name | Summary |
|---|---|
| [scenario](scenario.md) | The representation of a composed [TestContext](./index.md)'s steps.`fun scenario(scenario: `[`BaseScenario`](../../com.kaspersky.kaspresso.testcases.api.scenario/-base-scenario/index.md)`<Data>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [step](step.md) | The representation of a [TestContext](./index.md)'s step.`fun step(description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
