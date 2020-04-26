[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingWebAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Writes info to [compositeLogger](#).

### Parameters

`webAssertionProxy` - a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion](#) for
    interceptors calls.

`view` - an Android [android.view.View](https://developer.android.com/reference/android/view/View.html), on which [androidx.test.espresso.web.assertion.WebAssertion](#)
    is performed.

`result` - a result of [androidx.test.espresso.web.assertion.WebAssertion](#).