[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [DocLocScreenshotTestCase](index.md) / [getUiSafeProxyFromImplementation](./get-ui-safe-proxy-from-implementation.md)

# getUiSafeProxyFromImplementation

`fun <reified T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getUiSafeProxyFromImplementation(view: T): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)

Return a dynamic proxy over all interfaces that [view](get-ui-safe-proxy-from-implementation.md#com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase$getUiSafeProxyFromImplementation(com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase.getUiSafeProxyFromImplementation.T)/view) implements.

### Parameters

`view` - proxy target.

**Return**
a proxy over the given view.

