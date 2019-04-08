[kaspresso](../../index.md) / [android.support.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](./index.md)

# WebAssertionProxy

`class WebAssertionProxy<E> : WebAssertion<`[`E`](index.md#E)`>`

A proxy-wrapper of [WebAssertion](#) for interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WebAssertionProxy(webAssertion: WebAssertion<`[`E`](index.md#E)`>, atom: Atom<`[`E`](index.md#E)`>, matcher: Matcher<`[`E`](index.md#E)`>, interceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WebAssertionInterceptor`](../../com.kaspersky.kaspresso.interceptors/-web-assertion-interceptor/index.md)`>)`<br>A proxy-wrapper of [WebAssertion](#) for interceptors calls. |

### Functions

| Name | Summary |
|---|---|
| [checkResult](check-result.md) | `fun checkResult(view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`E`](index.md#E)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Calls interceptors before [WebViewAssertions.ResultCheckingWebAssertion.checkResult](#) on wrapped [webAssertion](#) is called. |
| [describe](describe.md) | `fun describe(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
