[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [WebAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: WebView?, result: Any): Unit`

Called to do some stuff before [androidx.test.espresso.web.assertion.WebAssertion.checkResult](#) is actually
called.

### Parameters

`webAssertionProxy` - a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion](#) for
    interceptors calls.

`view` - an Android [View](#), on which [androidx.test.espresso.web.assertion.WebAssertion](#) is performed.

`result` - a result of [androidx.test.espresso.web.assertion.WebAssertion](#).