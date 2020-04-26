[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [FailureLoggingProviderImpl](./index.md)

# FailureLoggingProviderImpl

`class FailureLoggingProviderImpl : `[`FailureLoggingProvider`](../-failure-logging-provider/index.md)

The implementation of the [FailureLoggingProvider](../-failure-logging-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [FailureLoggingProvider](../-failure-logging-provider/index.md) interface.`FailureLoggingProviderImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [logDescriptionAndThrow](log-description-and-throw.md) | Logs the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error) description got by [viewMatcher](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/viewMatcher) and throws the [error](log-description-and-throw.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logDescriptionAndThrow(kotlin.Throwable, org.hamcrest.Matcher((android.view.View)))/error).`fun logDescriptionAndThrow(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [logStackTrace](log-stack-trace.md) | Logs the [error](log-stack-trace.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$logStackTrace(kotlin.Throwable)/error)'s stacktrace.`fun logStackTrace(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [withLoggingOnFailure](with-logging-on-failure.md) | Invokes the given [action](with-logging-on-failure.md#com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl$withLoggingOnFailure(kotlin.Function0((com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl.withLoggingOnFailure.T)))/action) and logs if it fails.`fun <T> withLoggingOnFailure(action: () -> T): T` |
