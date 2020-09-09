[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [FailureLoggingProviderImpl](index.md) / [withLoggingOnFailure](./with-logging-on-failure.md)

# withLoggingOnFailure

`fun <T> withLoggingOnFailure(action: () -> T): T`

Invokes the given [action](with-logging-on-failure.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$withLoggingOnFailure(kotlin.Function0((com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl.withLoggingOnFailure.T)))/action) and logs if it fails.

### Parameters

`action` - the action to invoke.

### Exceptions

`Throwable` - if the [action](with-logging-on-failure.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$withLoggingOnFailure(kotlin.Function0((com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl.withLoggingOnFailure.T)))/action) invocation failed.

**Return**
the result of the [action](with-logging-on-failure.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$withLoggingOnFailure(kotlin.Function0((com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl.withLoggingOnFailure.T)))/action) invocation.

