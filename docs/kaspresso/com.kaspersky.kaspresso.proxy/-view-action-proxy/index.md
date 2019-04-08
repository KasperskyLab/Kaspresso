[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewActionProxy](./index.md)

# ViewActionProxy

`class ViewActionProxy : ViewAction`

A proxy-wrapper of [ViewAction](#) for interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewActionProxy(viewAction: ViewAction, interceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewActionInterceptor`](../../com.kaspersky.kaspresso.interceptors/-view-action-interceptor/index.md)`>)`<br>A proxy-wrapper of [ViewAction](#) for interceptors calls. |

### Functions

| Name | Summary |
|---|---|
| [getConstraints](get-constraints.md) | `fun getConstraints(): Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>`<br>Simply calls [ViewAction.getConstraints](#) on wrapped [viewAction](#). |
| [getDescription](get-description.md) | `fun getDescription(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Simply calls [ViewAction.getDescription](#) on wrapped [viewAction](#). |
| [perform](perform.md) | `fun perform(uiController: UiController, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Calls interceptors before [ViewAction.perform](#) on wrapped [viewAction](#) is called. |
