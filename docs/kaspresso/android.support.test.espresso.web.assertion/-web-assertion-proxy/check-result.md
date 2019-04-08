[kaspresso](../../index.md) / [android.support.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](index.md) / [checkResult](./check-result.md)

# checkResult

`protected fun checkResult(view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`E`](index.md#E)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Calls interceptors before [WebViewAssertions.ResultCheckingWebAssertion.checkResult](#) on wrapped [webAssertion](#) is
called.

### Parameters

`view` - a WebView that the Atom was evaluated on.

`result` - a result of atom evaluation.