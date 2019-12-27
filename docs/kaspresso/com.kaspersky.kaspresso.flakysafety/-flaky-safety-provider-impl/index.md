[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProviderImpl](./index.md)

# FlakySafetyProviderImpl

`class FlakySafetyProviderImpl : `[`FlakySafetyProvider`](../-flaky-safety-provider/index.md)

The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafetyProviderImpl(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [flakySafely](flaky-safely.md) | `fun <T> flakySafely(action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)<br>`fun <T> flakySafely(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)<br>Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl.flakySafely.T)))/action) flaky safely. |
