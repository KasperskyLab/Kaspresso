[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety.algorithm](../index.md) / [FlakySafetyAlgorithm](./index.md)

# FlakySafetyAlgorithm

`class FlakySafetyAlgorithm`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafetyAlgorithm(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [invokeFlakySafely](invoke-flaky-safely.md) | `fun <T> invokeFlakySafely(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: () -> `[`T`](invoke-flaky-safely.md#T)`): `[`T`](invoke-flaky-safely.md#T)<br>Attempts to invoke the given [action](invoke-flaky-safely.md#com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm$invokeFlakySafely(com.kaspersky.kaspresso.params.FlakySafetyParams, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm.invokeFlakySafely.T)))/action). |
