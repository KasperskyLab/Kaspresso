[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [ContinuouslyProviderImpl](index.md) / [continuously](./continuously.md)

# continuously

`fun <T> continuously(action: () -> `[`T`](continuously.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [ContinuouslyProvider.continuously](../-continuously-provider/continuously.md)

Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl.continuously.T)))/action) during set timeout.

### Parameters

`action` - the action to invoke.

### Exceptions

`Throwable` - if any of attempts failed.

**Return**
the [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl.continuously.T)))/action) invocation result.

`fun <T> continuously(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](continuously.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [ContinuouslyProvider.continuously](../-continuously-provider/continuously.md)

Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl$continuously(kotlin.Long, kotlin.Long, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl.continuously.T)))/action) during set timeout.

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

### Exceptions

`Throwable` - if any of attempts failed.

**Return**
the [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl$continuously(kotlin.Long, kotlin.Long, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl.continuously.T)))/action) invocation result.

