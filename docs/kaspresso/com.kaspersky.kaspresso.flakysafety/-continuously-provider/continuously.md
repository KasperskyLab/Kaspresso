[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [ContinuouslyProvider](index.md) / [continuously](./continuously.md)

# continuously

`abstract fun <T> continuously(action: () -> `[`T`](continuously.md#T)`): `[`T`](continuously.md#T)

Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider.continuously.T)))/action) during set timeout.

It can be helpful for checking of negative scenarios.

In opposite to [FlakySafetyProvider.flakySafely](../-flaky-safety-provider/flaky-safely.md) it does not skip last attempt after first success
and throws inside exception outside as soon as it was thrown

### Parameters

`action` - the action to invoke.

**Return**
the [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider$continuously(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider.continuously.T)))/action) invocation result.

`abstract fun <T> continuously(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: () -> `[`T`](continuously.md#T)`): `[`T`](continuously.md#T)

Invokes the given [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider$continuously(kotlin.Long, kotlin.Long, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider.continuously.T)))/action) during set timeout.

It can be helpful for checking of negative scenarios.

In opposite to [FlakySafetyProvider.flakySafely](../-flaky-safety-provider/flaky-safely.md) it does not skips last attempt after first success
and throws inside exception outside as soon as it was thrown

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

**Return**
the [action](continuously.md#com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider$continuously(kotlin.Long, kotlin.Long, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider.continuously.T)))/action) invocation result.

