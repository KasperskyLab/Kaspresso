[kaspresso](../../index.md) / [com.kaspersky.kaspresso.delegates](../index.md) / [WebInteractionDelegateImpl](./index.md)

# WebInteractionDelegateImpl

`open class WebInteractionDelegateImpl : WebInteractionDelegate`

An implementation of [WebInteractionDelegate](#), that delegates the [Web.WebInteraction](#)'s interface calls
to [AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md) and [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WebInteractionDelegateImpl(webInteraction: WebInteraction<*>)`<br>An implementation of [WebInteractionDelegate](#), that delegates the [Web.WebInteraction](#)'s interface calls to [AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md) and [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md). |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `open fun <E> check(webAssertion: WebAssertion<`[`E`](check.md#E)`>, atom: Atom<`[`E`](check.md#E)`>, matcher: Matcher<`[`E`](check.md#E)`>): WebInteractionDelegate`<br>Creates [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md) instance and delegates [Web.WebInteraction.check](#) call to it. |
| [perform](perform.md) | `open fun perform(webAction: Atom<*>): WebInteractionDelegate`<br>Creates [AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md) instance and delegates [Web.WebInteraction.perform](#) call to it. |
| [withElement](with-element.md) | `open fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate`<br>Calls [Web.WebInteraction.withElement](#) on wrapped [webInteraction](#). |
