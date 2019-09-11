[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose.pack](../index.md) / [ActionsOnElementsPack](index.md) / [or](./or.md)

# or

`fun <T> or(element: `[`T`](or.md#T)`, action: `[`T`](or.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`

Adds the [element](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Unit)))/element) of type [T](or.md#T) and the [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.T, kotlin.Unit)))/action) to [elements](#) and [actions](#) for future composing.

### Parameters

`element` - the interacted view.

`action` - actions or assertions on the interacted view.