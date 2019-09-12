[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [ViewAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(viewAssertion: ViewAssertion, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, exception: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called to do some stuff before [androidx.test.espresso.ViewAssertion.check](#) is actually called.

### Parameters

`viewAssertion` - responsible for performing assertions on a [View](https://developer.android.com/reference/android/view/View.html) element.

`view` - an Android [View](https://developer.android.com/reference/android/view/View.html), on which [viewAssertion](intercept.md#com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor$intercept(android.support.test.espresso.ViewAssertion, android.view.View, android.support.test.espresso.NoMatchingViewException)/viewAssertion) is performed.

`exception` - indicates that a given matcher did not match any elements in the view hierarchy.