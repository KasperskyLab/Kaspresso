[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewActionProxy](./index.md)

# ViewActionProxy

`class ViewActionProxy : ViewAction`

The proxy-wrapper of [ViewAction](#) for watcher interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewActionProxy(viewAction: ViewAction, watcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewActionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)`>)`<br>The proxy-wrapper of [ViewAction](#) for watcher interceptors calls. |

### Functions

| Name | Summary |
|---|---|
| [getConstraints](get-constraints.md) | `fun getConstraints(): Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>`<br>Simply calls [ViewAction.getConstraints](#) on wrapped [viewAction](#). |
| [getDescription](get-description.md) | `fun getDescription(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Simply calls [ViewAction.getDescription](#) on wrapped [viewAction](#). |
| [perform](perform.md) | `fun perform(uiController: UiController, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Calls watcher interceptors before [ViewAction.perform](#) on wrapped [viewAction](#) is called. |
