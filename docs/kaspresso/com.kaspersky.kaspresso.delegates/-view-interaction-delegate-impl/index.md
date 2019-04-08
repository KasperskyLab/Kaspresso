[kaspresso](../../index.md) / [com.kaspersky.kaspresso.delegates](../index.md) / [ViewInteractionDelegateImpl](./index.md)

# ViewInteractionDelegateImpl

`open class ViewInteractionDelegateImpl : ViewInteractionDelegate`

An implementation of [ViewInteractionDelegate](#), that delegates the [ViewInteraction](#)'s interface calls
to [ViewActionProxy](../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md) and [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewInteractionDelegateImpl(viewInteraction: ViewInteraction)`<br>An implementation of [ViewInteractionDelegate](#), that delegates the [ViewInteraction](#)'s interface calls to [ViewActionProxy](../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md) and [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md). |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `open fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate`<br>`open fun check(function: (`[`View`](https://developer.android.com/reference/android/view/View.html)`, NoMatchingViewException?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ViewInteractionDelegate`<br>Creates [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md) instance and delegates [ViewInteraction.check](#) call to it. |
| [inRoot](in-root.md) | `open fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate`<br>Calls [ViewInteraction.inRoot](#) on [viewInteraction](#). |
| [perform](perform.md) | `open fun perform(viewAction: ViewAction): ViewInteractionDelegate`<br>Creates [ViewActionProxy](../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md) instance and delegates [ViewInteraction.perform](#) call to it. |
| [withFailureHandler](with-failure-handler.md) | `open fun withFailureHandler(function: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ViewInteractionDelegate`<br>Calls [ViewInteraction.withFailureHandler](#) on wrapped [viewInteraction](#). |
