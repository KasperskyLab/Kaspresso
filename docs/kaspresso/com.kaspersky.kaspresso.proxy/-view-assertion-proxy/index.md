[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewAssertionProxy](./index.md)

# ViewAssertionProxy

`class ViewAssertionProxy : ViewAssertion`

The proxy-wrapper of [ViewAssertion](#) for watcher interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The proxy-wrapper of [ViewAssertion](#) for watcher interceptors calls.`ViewAssertionProxy(viewAssertion: ViewAssertion, watcherInterceptors: List<`[`ViewAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)`>)` |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | Calls watcher interceptors before [ViewAssertion.check](#) on wrapped [viewAssertion](#) is called.`fun check(view: View?, noViewFoundException: NoMatchingViewException?): Unit` |
