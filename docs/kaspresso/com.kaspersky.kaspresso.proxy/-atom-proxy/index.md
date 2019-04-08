[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [AtomProxy](./index.md)

# AtomProxy

`class AtomProxy<R> : Atom<`[`R`](index.md#R)`>`

A proxy-wrapper of [Atom](#) for interceptors calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AtomProxy(atom: Atom<`[`R`](index.md#R)`>, interceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AtomInterceptor`](../../com.kaspersky.kaspresso.interceptors/-atom-interceptor/index.md)`>)`<br>A proxy-wrapper of [Atom](#) for interceptors calls. |

### Functions

| Name | Summary |
|---|---|
| [getArguments](get-arguments.md) | `fun getArguments(elementContext: ElementReference?): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`<br>Simply calls [Atom.getArguments](#) on wrapped [atom](#). |
| [getScript](get-script.md) | `fun getScript(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Simply calls [Atom.getScript](#) on wrapped [atom](#). |
| [transform](transform.md) | `fun transform(evaluation: Evaluation?): `[`R`](index.md#R)<br>Calls interceptors before [Atom.transform](#) on wrapped [atom](#) is called. |
