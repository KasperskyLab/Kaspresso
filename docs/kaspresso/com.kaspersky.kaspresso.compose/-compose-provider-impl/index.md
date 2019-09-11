[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose](../index.md) / [ComposeProviderImpl](./index.md)

# ComposeProviderImpl

`class ComposeProviderImpl : `[`ComposeProvider`](../-compose-provider/index.md)

The implementation of the [ComposeProvider](../-compose-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ComposeProviderImpl(kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)`)`<br>The implementation of the [ComposeProvider](../-compose-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [compose](compose.md) | `fun compose(block: `[`ActionsOnElementsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.ComposeProviderImpl$compose(kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack, kotlin.Unit)))/block) of actions with their views to invoke on in one composite action that succeeds if at least one of it's parts succeeds.`fun <T> `[`T`](compose.md#T)`.compose(block: `[`ActionsPack`](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)`<`[`T`](compose.md#T)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`<br>Composes a [block](compose.md#com.kaspersky.kaspresso.compose.ComposeProviderImpl$compose(com.kaspersky.kaspresso.compose.ComposeProviderImpl.compose.T, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack((com.kaspersky.kaspresso.compose.ComposeProviderImpl.compose.T)), kotlin.Unit)))/block) of actions on the given view of type [T](compose.md#T) in one composite action that succeeds if at least one of it's parts succeeds. |
