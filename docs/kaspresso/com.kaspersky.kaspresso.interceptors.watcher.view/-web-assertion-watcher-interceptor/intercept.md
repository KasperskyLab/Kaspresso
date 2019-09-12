[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [WebAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called to do some stuff before [androidx.test.espresso.web.assertion.WebAssertion.checkResult](#) is actually
called.

### Parameters

`webAssertionProxy` - a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion](#) for
    interceptors calls.

`view` - an Android [View](#), on which [androidx.test.espresso.web.assertion.WebAssertion](#) is performed.

`result` - a result of [androidx.test.espresso.web.assertion.WebAssertion](#).