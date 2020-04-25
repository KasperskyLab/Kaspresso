[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [FailureLoggingProviderImpl](index.md) / [logDescriptionAndThrow](./log-description-and-throw.md)

# logDescriptionAndThrow

`fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?): Unit`

Logs the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error) description got by [viewMatcher](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/viewMatcher) and throws the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error).

### Parameters

`error` - the error to be described.

`viewMatcher` - the view matcher to get description from.

### Exceptions

`Throwable` - the given [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error).