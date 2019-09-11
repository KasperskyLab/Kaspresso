[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.testcontext](../index.md) / [TestContext](./index.md)

# TestContext

`class TestContext<Data> : `[`BaseTestContext`](../-base-test-context/index.md)

The special class to operate with in user scenario.
Provides [step](step.md) and [scenario](scenario.md) methods in "run" section to build a test.

### Parameters

`Data` - data created in before section.

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: `[`Data`](index.md#Data) |

### Inherited Properties

| Name | Summary |
|---|---|
| [adbServer](../-base-test-context/adb-server.md) | `val adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md) |
| [device](../-base-test-context/device.md) | `val device: `[`Device`](../../com.kaspersky.kaspresso.device/-device/index.md) |
| [testLogger](../-base-test-context/test-logger.md) | `val testLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) |

### Functions

| Name | Summary |
|---|---|
| [scenario](scenario.md) | `fun scenario(scenario: `[`BaseScenario`](../../com.kaspersky.kaspresso.testcases.api.scenario/-base-scenario/index.md)`<`[`Data`](index.md#Data)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>The representation of a composed [TestContext](./index.md)'s steps. |
| [step](step.md) | `fun step(description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>The representation of a [TestContext](./index.md)'s step. |
