[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [AtomProxy](./index.md)

# AtomProxy

`class AtomProxy<T> : Atom<T>`

The proxy-wrapper of [Atom](#) for watcher interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The proxy-wrapper of [Atom](#) for watcher interceptors calls.`AtomProxy(atom: Atom<T>, matcher: Matcher<*>, watcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AtomWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)`>)` |

### Properties

| Name | Summary |
|---|---|
| [atom](atom.md) | `val atom: Atom<T>` |
| [matcher](matcher.md) | `val matcher: Matcher<*>` |

### Functions

| Name | Summary |
|---|---|
| [getArguments](get-arguments.md) | Simply calls [Atom.getArguments](#) on wrapped [atom](atom.md).`fun getArguments(elementContext: ElementReference?): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [getScript](get-script.md) | Simply calls [Atom.getScript](#) on wrapped [atom](atom.md).`fun getScript(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [transform](transform.md) | Calls watcher interceptors before [Atom.transform](#) on wrapped [atom](atom.md) is called.`fun transform(evaluation: Evaluation?): T` |
