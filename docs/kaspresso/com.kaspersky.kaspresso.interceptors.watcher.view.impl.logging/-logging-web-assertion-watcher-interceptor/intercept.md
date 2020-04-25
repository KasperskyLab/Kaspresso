[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingWebAssertionWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: WebView?, result: Any): Unit`

Writes info to [compositeLogger](#).

### Parameters

`webAssertionProxy` - a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion](#) for
    interceptors calls.

`view` - an Android [android.view.View](#), on which [androidx.test.espresso.web.assertion.WebAssertion](#)
    is performed.

`result` - a result of [androidx.test.espresso.web.assertion.WebAssertion](#).