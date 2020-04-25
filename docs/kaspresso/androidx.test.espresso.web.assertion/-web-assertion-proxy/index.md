[kaspresso](../../index.md) / [androidx.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](./index.md)

# WebAssertionProxy

`class WebAssertionProxy<E> : WebAssertion<E>`

A proxy-wrapper of [WebAssertion](#) for [watcherInterceptors](#) calls.

Uses [WebViewAssertions.ResultCheckingWebAssertion](#) class, that has package-local access in Espresso, so it has to be
in the same package.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | A proxy-wrapper of [WebAssertion](#) for [watcherInterceptors](#) calls.`WebAssertionProxy(webAssertion: WebAssertion<E>, matcher: Matcher<*>, watcherInterceptors: List<`[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>)` |

### Properties

| Name | Summary |
|---|---|
| [matcher](matcher.md) | `val matcher: Matcher<*>` |
| [webAssertion](web-assertion.md) | `val webAssertion: WebAssertion<E>` |

### Functions

| Name | Summary |
|---|---|
| [checkResult](check-result.md) | Calls [watcherInterceptors](#) before [WebViewAssertions.ResultCheckingWebAssertion.checkResult](#) on wrapped [webAssertion](web-assertion.md) is called.`fun checkResult(view: WebView?, result: E): Unit` |
