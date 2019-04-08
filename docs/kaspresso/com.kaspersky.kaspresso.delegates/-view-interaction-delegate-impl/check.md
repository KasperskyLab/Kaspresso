[kaspresso](../../index.md) / [com.kaspersky.kaspresso.delegates](../index.md) / [ViewInteractionDelegateImpl](index.md) / [check](./check.md)

# check

`open fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate`

Creates [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md) instance and delegates [ViewInteraction.check](#) call to it.

### Parameters

`viewAssertion` - an assertion to execute.

**Return**
an existing [ViewInteractionDelegateImpl](index.md) instance.

`open fun check(function: (`[`View`](https://developer.android.com/reference/android/view/View.html)`, NoMatchingViewException?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ViewInteractionDelegate`

Creates [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md) instance and delegates [ViewInteraction.check](#) call to it.

### Parameters

`function` - a parameter with which the [ViewAssertion](#) will be created.

**Return**
an existing [ViewInteractionDelegateImpl](index.md) instance.

