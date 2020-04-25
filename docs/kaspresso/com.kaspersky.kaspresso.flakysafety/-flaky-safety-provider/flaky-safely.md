[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProvider](index.md) / [flakySafely](./flaky-safely.md)

# flakySafely

`abstract fun <T> flakySafely(action: () -> T): T`

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) flaky safely.

### Parameters

`action` - the action to invoke.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) invocation result.

`abstract fun <T> flakySafely(timeoutMs: Long? = null, intervalMs: Long? = null, allowedExceptions: Set<Class<out Throwable>>? = null, failureMessage: String? = null, action: () -> T): T`

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) flaky safely.

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`allowedExceptions` - the set of exceptions that allow to continue an attempt of execution.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider.flakySafely.T)))/action) invocation result.

