[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [ContinuouslyProviderImpl](./index.md)

# ContinuouslyProviderImpl

`class ContinuouslyProviderImpl : `[`ContinuouslyProvider`](../-continuously-provider/index.md)

The implementation of the [ContinuouslyProvider](../-continuously-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ContinuouslyProviderImpl(params: `[`ContinuouslyParams`](../../com.kaspersky.kaspresso.params/-continuously-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [ContinuouslyProvider](../-continuously-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [continuously](continuously.md) | `fun <T> continuously(action: () -> `[`T`](continuously.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> continuously(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](continuously.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl.continuously.T)))/action) during set timeout. |
