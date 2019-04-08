[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [FailureInterceptor](index.md) / [interceptAndThrow](./intercept-and-throw.md)

# interceptAndThrow

`abstract fun interceptAndThrow(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Reference to this method is provided as [android.support.test.espresso.FailureHandler](#) and is called on failures,
to do some stuff and throw an exception further.

### Parameters

`error` - the reason of failure.

`viewMatcher` - a matcher, which corresponded to the view on which the error occurred.