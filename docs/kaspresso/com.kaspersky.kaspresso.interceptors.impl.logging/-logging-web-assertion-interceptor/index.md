[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingWebAssertionInterceptor](./index.md)

# LoggingWebAssertionInterceptor

`class LoggingWebAssertionInterceptor : `[`WebAssertionInterceptor`](../../com.kaspersky.kaspresso.interceptors/-web-assertion-interceptor/index.md)

An implementation of [WebAssertionInterceptor](../../com.kaspersky.kaspresso.interceptors/-web-assertion-interceptor/index.md) that logs info about
[android.support.test.espresso.web.assertion.WebAssertion](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingWebAssertionInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)`)`<br>An implementation of [WebAssertionInterceptor](../../com.kaspersky.kaspresso.interceptors/-web-assertion-interceptor/index.md) that logs info about [android.support.test.espresso.web.assertion.WebAssertion](#). |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
