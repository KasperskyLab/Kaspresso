[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [FailureLoggingProvider](index.md) / [withLoggingOnFailure](./with-logging-on-failure.md)

# withLoggingOnFailure

`abstract fun <T> withLoggingOnFailure(action: () -> `[`T`](with-logging-on-failure.md#T)`): `[`T`](with-logging-on-failure.md#T)

Invokes the given [action](with-logging-on-failure.md#com.kaspersky.kaspresso.failure.FailureLoggingProvider$withLoggingOnFailure(kotlin.Function0((com.kaspersky.kaspresso.failure.FailureLoggingProvider.withLoggingOnFailure.T)))/action) and logs if it fails.

### Parameters

`action` - the action to invoke.

**Return**
the result of the [action](with-logging-on-failure.md#com.kaspersky.kaspresso.failure.FailureLoggingProvider$withLoggingOnFailure(kotlin.Function0((com.kaspersky.kaspresso.failure.FailureLoggingProvider.withLoggingOnFailure.T)))/action) invocation.

