[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [FailureLoggingProvider](index.md) / [logDescriptionAndThrow](./log-description-and-throw.md)

# logDescriptionAndThrow

`abstract fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?): Unit`

Logs the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProvider$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error) description got by [viewMatcher](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProvider$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/viewMatcher) and throws the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProvider$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error).

### Parameters

`error` - the error to be described.

`viewMatcher` - the view matcher to get description from.