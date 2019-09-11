[kaspresso](../../index.md) / [com.kaspersky.kaspresso.failure](../index.md) / [LoggingFailureHandler](./index.md)

# LoggingFailureHandler

`class LoggingFailureHandler : FailureHandler, `[`FailureLoggingProvider`](../-failure-logging-provider/index.md)

The implementation of the [FailureHandler](#) interface that logs rich description of failure.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingFailureHandler(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [FailureHandler](#) interface that logs rich description of failure. |

### Functions

| Name | Summary |
|---|---|
| [handle](handle.md) | `fun handle(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Calls [logDescriptionAndThrow](#) on each failure. |
