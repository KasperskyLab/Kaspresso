[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProviderSimpleImpl](index.md) / [flakySafely](./flaky-safely.md)

# flakySafely

`fun <T> flakySafely(action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)

Overrides [FlakySafetyProvider.flakySafely](../-flaky-safety-provider/flaky-safely.md)

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl.flakySafely.T)))/action) flaky safely.

### Parameters

`action` - the action to invoke.

### Exceptions

`Throwable` - if all attempts failed.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl.flakySafely.T)))/action) invocation result.

`fun <T> flakySafely(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)

Overrides [FlakySafetyProvider.flakySafely](../-flaky-safety-provider/flaky-safely.md)

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl.flakySafely.T)))/action) flaky safely.

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`allowedExceptions` - the set of exceptions, if caught, attempt will continue.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

### Exceptions

`Throwable` - if all attempts failed.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl.flakySafely.T)))/action) invocation result.

