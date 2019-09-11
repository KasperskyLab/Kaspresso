[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose.pack](../index.md) / [ActionsOnElementsPack](./index.md)

# ActionsOnElementsPack

`class ActionsOnElementsPack`

The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-compose-provider/compose.md) method.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ActionsOnElementsPack()`<br>The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-compose-provider/compose.md) method. |

### Functions

| Name | Summary |
|---|---|
| [or](or.md) | `fun <T> or(element: `[`T`](or.md#T)`, action: `[`T`](or.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`<br>Adds the [element](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Unit)))/element) of type [T](or.md#T) and the [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Unit)))/action) to [elements](#) and [actions](#) for future composing. |
