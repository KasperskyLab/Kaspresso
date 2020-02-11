[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety.algorithm](../index.md) / [FlakySafetyAlgorithm](index.md) / [invokeFlakySafely](./invoke-flaky-safely.md)

# invokeFlakySafely

`fun <T> invokeFlakySafely(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: () -> `[`T`](invoke-flaky-safely.md#T)`): `[`T`](invoke-flaky-safely.md#T)

Attempts to invoke the given [action](invoke-flaky-safely.md#com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm$invokeFlakySafely(com.kaspersky.kaspresso.params.FlakySafetyParams, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm.invokeFlakySafely.T)))/action).

