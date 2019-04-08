[kaspresso](../../index.md) / [com.kaspersky.kaspresso.delegates](../index.md) / [DataInteractionDelegateImpl](./index.md)

# DataInteractionDelegateImpl

`open class DataInteractionDelegateImpl : DataInteractionDelegate`

An implementation of [DataInteractionDelegate](#), that delegates the [DataInteraction](#)'s interface calls
to [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DataInteractionDelegateImpl(dataInteraction: DataInteraction)`<br>An implementation of [DataInteractionDelegate](#), that delegates the [DataInteraction](#)'s interface calls to [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md). |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `open fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate`<br>Creates [ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md) instance and delegates [DataInteraction.check](#) call to it. |
| [onChildView](on-child-view.md) | `open fun onChildView(childMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>): DataInteractionDelegate`<br>Calls [DataInteraction.onChildView](#) on wrapped [dataInteraction](#). |
