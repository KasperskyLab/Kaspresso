[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingFailureInterceptor](index.md) / [interceptAndThrow](./intercept-and-throw.md)

# interceptAndThrow

`fun interceptAndThrow(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [FailureInterceptor.interceptAndThrow](../../com.kaspersky.kaspresso.interceptors/-failure-interceptor/intercept-and-throw.md)

Writes info to [logger](#) and throws an exception further.

### Parameters

`error` - the reason of failure.

`viewMatcher` - a matcher, which corresponded to the view on which the error occurred.