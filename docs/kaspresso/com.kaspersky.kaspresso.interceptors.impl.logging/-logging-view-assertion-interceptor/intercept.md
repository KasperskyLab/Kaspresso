[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingViewAssertionInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(viewAssertion: ViewAssertion, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, exception: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [ViewAssertionInterceptor.intercept](../../com.kaspersky.kaspresso.interceptors/-view-assertion-interceptor/intercept.md)

Writes info to [logger](#).

### Parameters

`viewAssertion` - responsible for performing assertions on a [View](https://developer.android.com/reference/android/view/View.html) element.

`view` - an Android [View](https://developer.android.com/reference/android/view/View.html), on which [viewAssertion](intercept.md#com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewAssertionInterceptor$intercept(android.support.test.espresso.ViewAssertion, android.view.View, android.support.test.espresso.NoMatchingViewException)/viewAssertion) is performed.

`exception` - indicates that a given matcher did not match any elements in the view hierarchy.