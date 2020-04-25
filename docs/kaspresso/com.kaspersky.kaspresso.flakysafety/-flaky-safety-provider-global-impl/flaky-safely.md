[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProviderGlobalImpl](index.md) / [flakySafely](./flaky-safely.md)

# flakySafely

`fun <T> flakySafely(action: () -> T): T`

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl.flakySafely.T)))/action) flaky safely.

### Parameters

`action` - the action to invoke.

### Exceptions

`Throwable` - if all attempts failed.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl.flakySafely.T)))/action) invocation result.

`fun <T> flakySafely(timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, failureMessage: String?, action: () -> T): T`

Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl.flakySafely.T)))/action) flaky safely.

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`allowedExceptions` - the set of exceptions, if caught, attempt will continue.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

### Exceptions

`Throwable` - if all attempts failed.

**Return**
the [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl$flakySafely(kotlin.Long, kotlin.Long, kotlin.collections.Set((java.lang.Class((kotlin.Throwable)))), kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl.flakySafely.T)))/action) invocation result.

