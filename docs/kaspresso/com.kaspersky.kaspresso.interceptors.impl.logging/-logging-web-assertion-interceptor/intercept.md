[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingWebAssertionInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [WebAssertionInterceptor.intercept](../../com.kaspersky.kaspresso.interceptors/-web-assertion-interceptor/intercept.md)

Writes info to [logger](#).

### Parameters

`webAssertionProxy` - a proxy-wrapper of [android.support.test.espresso.web.assertion.WebAssertion](#) for
    interceptors calls.

`view` - an Android [android.view.View](https://developer.android.com/reference/android/view/View.html), on which [android.support.test.espresso.web.assertion.WebAssertion](#)
    is performed.

`result` - a result of [android.support.test.espresso.web.assertion.WebAssertion](#).