[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [AtomProxy](./index.md)

# AtomProxy

`class AtomProxy<T> : Atom<`[`T`](index.md#T)`>`

The proxy-wrapper of [Atom](#) for watcher interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AtomProxy(atom: Atom<`[`T`](index.md#T)`>, matcher: Matcher<*>, watcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AtomWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)`>)`<br>The proxy-wrapper of [Atom](#) for watcher interceptors calls. |

### Properties

| Name | Summary |
|---|---|
| [atom](atom.md) | `val atom: Atom<`[`T`](index.md#T)`>` |
| [matcher](matcher.md) | `val matcher: Matcher<*>` |

### Functions

| Name | Summary |
|---|---|
| [getArguments](get-arguments.md) | `fun getArguments(elementContext: ElementReference?): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>Simply calls [Atom.getArguments](#) on wrapped [atom](atom.md). |
| [getScript](get-script.md) | `fun getScript(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Simply calls [Atom.getScript](#) on wrapped [atom](atom.md). |
| [transform](transform.md) | `fun transform(evaluation: Evaluation?): `[`T`](index.md#T)<br>Calls watcher interceptors before [Atom.transform](#) on wrapped [atom](atom.md) is called. |
