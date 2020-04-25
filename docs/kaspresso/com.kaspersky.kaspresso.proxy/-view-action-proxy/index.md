[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewActionProxy](./index.md)

# ViewActionProxy

`class ViewActionProxy : ViewAction`

The proxy-wrapper of [ViewAction](#) for watcher interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The proxy-wrapper of [ViewAction](#) for watcher interceptors calls.`ViewActionProxy(viewAction: ViewAction, watcherInterceptors: List<`[`ViewActionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)`>)` |

### Functions

| Name | Summary |
|---|---|
| [getConstraints](get-constraints.md) | Simply calls [ViewAction.getConstraints](#) on wrapped [viewAction](#).`fun getConstraints(): Matcher<View>` |
| [getDescription](get-description.md) | Simply calls [ViewAction.getDescription](#) on wrapped [viewAction](#).`fun getDescription(): String` |
| [perform](perform.md) | Calls watcher interceptors before [ViewAction.perform](#) on wrapped [viewAction](#) is called.`fun perform(uiController: UiController, view: View): Unit` |
