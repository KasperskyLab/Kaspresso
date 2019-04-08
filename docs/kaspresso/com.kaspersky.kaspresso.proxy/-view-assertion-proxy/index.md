[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewAssertionProxy](./index.md)

# ViewAssertionProxy

`class ViewAssertionProxy : ViewAssertion`

A proxy-wrapper of [ViewAssertion](#) for interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewAssertionProxy(viewAssertion: ViewAssertion, interceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewAssertionInterceptor`](../../com.kaspersky.kaspresso.interceptors/-view-assertion-interceptor/index.md)`>)`<br>A proxy-wrapper of [ViewAssertion](#) for interceptors calls. |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, noViewFoundException: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Calls interceptors before [ViewAssertion.check](#) on wrapped [viewAssertion](#) is called. |

### Extension Functions

| Name | Summary |
|---|---|
| [describe](../../android.support.test.espresso.assertion/android.support.test.espresso.-view-assertion/describe.md) | `fun ViewAssertion?.describe(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
