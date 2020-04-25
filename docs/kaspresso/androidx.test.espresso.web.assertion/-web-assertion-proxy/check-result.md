[kaspresso](../../index.md) / [androidx.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](index.md) / [checkResult](./check-result.md)

# checkResult

`protected fun checkResult(view: WebView?, result: E): Unit`

Calls [watcherInterceptors](#) before [WebViewAssertions.ResultCheckingWebAssertion.checkResult](#) on wrapped
[webAssertion](web-assertion.md) is called.

### Parameters

`view` - a WebView that the Atom was evaluated on.

`result` - a result of atom evaluation.