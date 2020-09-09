[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [FailureLoggingProviderImpl](index.md) / [logDescriptionAndThrow](./log-description-and-throw.md)

# logDescriptionAndThrow

`fun logDescriptionAndThrow(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Logs the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error) description got by [viewMatcher](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/viewMatcher) and throws the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error).

### Parameters

`error` - the error to be described.

`viewMatcher` - the view matcher to get description from.

### Exceptions

`Throwable` - the given [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error).