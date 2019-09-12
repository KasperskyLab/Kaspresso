[kaspresso](../../index.md) / [android.support.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](./index.md)

# WebAssertionProxy

`class WebAssertionProxy<E> : WebAssertion<`[`E`](index.md#E)`>`

A proxy-wrapper of [WebAssertion](#) for [watcherInterceptors](#) calls.

Uses [WebViewAssertions.ResultCheckingWebAssertion](#) class, that has package-local access in Espresso, so it has to be
in the same package.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WebAssertionProxy(webAssertion: WebAssertion<`[`E`](index.md#E)`>, matcher: Matcher<*>, watcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>)`<br>A proxy-wrapper of [WebAssertion](#) for [watcherInterceptors](#) calls. |

### Properties

| Name | Summary |
|---|---|
| [matcher](matcher.md) | `val matcher: Matcher<*>` |
| [webAssertion](web-assertion.md) | `val webAssertion: WebAssertion<`[`E`](index.md#E)`>` |

### Functions

| Name | Summary |
|---|---|
| [checkResult](check-result.md) | `fun checkResult(view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`E`](index.md#E)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Calls [watcherInterceptors](#) before [WebViewAssertions.ResultCheckingWebAssertion.checkResult](#) on wrapped [webAssertion](web-assertion.md) is called. |
