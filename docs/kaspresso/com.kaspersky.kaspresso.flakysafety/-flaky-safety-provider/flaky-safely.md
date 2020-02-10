[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProvider](index.md) / [flakySafely](./flaky-safely.md)

# flakySafely

`abstract fun <T> flakySafely(action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) flaky safely.

### Parameters

`action` - the action to invoke.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) invocation result.

`abstract fun <T> flakySafely(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`? = null, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>? = null, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) flaky safely.

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of execution.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) invocation result.

