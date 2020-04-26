[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [ContinuouslyProviderImpl](./index.md)

# ContinuouslyProviderImpl

`class ContinuouslyProviderImpl : `[`ContinuouslyProvider`](../-continuously-provider/index.md)

The implementation of the [ContinuouslyProvider](../-continuously-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [ContinuouslyProvider](../-continuously-provider/index.md) interface.`ContinuouslyProviderImpl(kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [continuously](continuously.md) | Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl.continuously.T)))/action) during set timeout.`fun <T> continuously(action: () -> T): T`<br>`fun <T> continuously(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> T): T` |
