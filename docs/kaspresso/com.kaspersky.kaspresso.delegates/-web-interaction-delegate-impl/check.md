[kaspresso](../../index.md) / [com.kaspersky.kaspresso.delegates](../index.md) / [WebInteractionDelegateImpl](index.md) / [check](./check.md)

# check

`open fun <E> check(webAssertion: WebAssertion<`[`E`](check.md#E)`>, atom: Atom<`[`E`](check.md#E)`>, matcher: Matcher<`[`E`](check.md#E)`>): WebInteractionDelegate`

Creates [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md) instance and delegates [Web.WebInteraction.check](#) call to it.

### Parameters

`webAssertion` - an assertion to execute.

`atom` - an atom to pass to [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md).

`matcher` - a matcher to pass to [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md).

**Return**
a new instance of [WebInteractionDelegate](#).

